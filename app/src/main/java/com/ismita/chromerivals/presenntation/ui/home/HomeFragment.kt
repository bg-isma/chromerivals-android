package com.ismita.chromerivals.presenntation.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.ActivityMainNavigationBinding
import com.ismita.chromerivals.databinding.HomeFragmentBinding
import com.ismita.chromerivals.data.model.theme.ThemeDB
import com.ismita.chromerivals.data.model.pedia.typesEnum.CategoryType
import com.ismita.chromerivals.presenntation.adapters.EventsAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import com.ismita.chromerivals.utils.CheckNetworkConnection
import com.ismita.chromerivals.utils.ChipUtils.getCustomMaterialChip
import com.ismita.chromerivals.utils.ChipUtils.selectChip
import com.ismita.chromerivals.utils.extensions.LinearLayoutExtension.toHorizontalLayoutManager


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    private lateinit var upComingEventsListAdapter: EventsAdapter
    private lateinit var currentEventsListAdapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callNetworkConnection()

        homeViewModel.getCurrentEvents()
        homeViewModel.getUpcomingEvents()
        homeViewModel.getTheme()


        binding.nationSwitch.setOnClickListener {
            if (binding.nationSwitch.isChecked) changeAppTheme(R.drawable.ani_background, R.drawable.ic_ani_logo)
            else changeAppTheme(R.drawable.bcu_background, R.drawable.ic_bcu_logo)

            val theme = homeViewModel.theme.value
            if (theme != null) {
                theme.nation = !theme.nation
                homeViewModel.updateTheme(theme)
            } else {
                homeViewModel.insertTheme(ThemeDB(nation = binding.nationSwitch.isChecked))
            }
        }

        setCategoriesChips()
        setUpcomingEventsList()
        setCurrentEventList()

        homeViewModel.upComingEvents.observe(viewLifecycleOwner) { events ->
            upComingEventsListAdapter.setData(events)
        }

        homeViewModel.currentEvents.observe(viewLifecycleOwner) { events ->
            if (events.isEmpty()) binding.currentEventListNoEvents.visibility = View.VISIBLE
            else binding.currentEventListNoEvents.visibility = View.GONE
            currentEventsListAdapter.setData(events)
        }

        homeViewModel.theme.observe(viewLifecycleOwner) { theme ->
            if (theme != null) setUpSelectedTheme(theme)
        }

    }

    private fun setUpSelectedTheme(theme: ThemeDB) {
        if (theme.nation) changeAppTheme(R.drawable.ani_background, R.drawable.ic_ani_logo)
        else changeAppTheme(R.drawable.bcu_background, R.drawable.ic_bcu_logo)
        binding.nationSwitch.isChecked = theme.nation
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeAppTheme(colorBackground: Int, logoDrawable: Int) {
        val parentBinding = ActivityMainNavigationBinding.bind(requireActivity().findViewById(R.id.container))
        binding.appPrimaryBackground.background = resources.getDrawable(colorBackground, null)
        parentBinding.navView.menu.getItem(0).icon = resources.getDrawable(logoDrawable, null)
    }

    private fun setCategoriesChips() {
        if (context != null) {
            val categoriesType = listOf(
                CategoryType.All,
                CategoryType.Outpost,
                CategoryType.Mothership
            )
            val chips = getChipsFromCategories(categoriesType)
            selectChip(chips, 0)
            chips.forEachIndexed { index, chip ->
                chip.setOnClickListener {
                    selectChip(chips.toList(), index)
                    homeViewModel.filterByCategoryType(categoriesType[index])
                }
                binding.categoriesListFilter.addView(chip)
            }
        }
    }

    private fun getChipsFromCategories(categories: List<CategoryType>): ArrayList<Chip> {
        val chips = ArrayList<Chip>()
        categories.forEach { categoryType ->
            val chip = getCustomMaterialChip(
                context = requireContext(),
                text = categoryType.toString(),
                bgColor = R.color.white,
                strokeColor = R.color.black,
                textAppearance = R.style.events_chip_not_selected,
                strokeWidth = 2f,
                minHeight = 75f
            )
            chips.add(chip)
        }
        return chips
    }

    private fun setCurrentEventList() {
        binding.currentEventList.layoutManager = LinearLayoutManager(context).toHorizontalLayoutManager()
        currentEventsListAdapter = EventsAdapter(homeViewModel)
        binding.currentEventList.adapter = currentEventsListAdapter
    }

    private fun setUpcomingEventsList() {
        binding.upcomingEventsList.layoutManager = LinearLayoutManager(context)
        upComingEventsListAdapter = EventsAdapter(null)
        binding.upcomingEventsList.adapter = upComingEventsListAdapter
    }

    private fun refreshHomeFragment() {
        val currentEvents = homeViewModel.currentEvents.value
        val upcomingEvent = homeViewModel.upComingEvents.value
        if (currentEvents != null && currentEvents.isEmpty()) homeViewModel.getCurrentEvents()
        if (upcomingEvent != null && upcomingEvent.isEmpty()) homeViewModel.getUpcomingEvents()
    }

    private fun callNetworkConnection() {
        activity?.let { CheckNetworkConnection(it.application) }?.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) refreshHomeFragment()
        }
    }

}