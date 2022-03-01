package com.ismita.chromerivals.presenntation.ui.pedia

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.SearchFragmentBinding
import com.ismita.chromerivals.data.model.pedia.typesEnum.CategoryType
import com.ismita.chromerivals.utils.ChipUtils.getCustomMaterialChip
import com.ismita.chromerivals.utils.ChipUtils.selectChip
import com.ismita.chromerivals.presenntation.adapters.HistoryAdapter
import com.ismita.chromerivals.presenntation.adapters.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: SearchFragmentBinding

    private lateinit var searchListAdapter: SearchAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var capabilities: List<Chip>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchViewModel.onCreate()

        setCategoriesChips()
        setSearchList()
        setSearchHistoryList()

        binding.searchInput.setOnQueryTextListener(this)
        binding.searchInput.setOnQueryTextFocusChangeListener { _, _ ->
            openHistory()
        }

        binding.closeHistoryBtn.setOnClickListener {
            closeHistoryAndClearFocus()
        }

        binding.resetHistoryBtn.setOnClickListener {
            searchViewModel.deleteAllHistory()
        }

        searchViewModel.searchResult.observe(viewLifecycleOwner , { pediaElements ->
            searchListAdapter.setData(pediaElements)
        })

        searchViewModel.historyElements.observe(viewLifecycleOwner, { historyElements ->
            historyAdapter.setData(historyElements)
        })
    }

    private fun setSearchHistoryList() {
        binding.historyList.layoutManager = LinearLayoutManager(context)
        historyAdapter = HistoryAdapter(searchViewModel, this)
        binding.historyList.adapter = historyAdapter
    }

    private fun setCategoriesChips() {
        if (context != null) {
            val categoriesType = listOf(
                CategoryType.All,
                CategoryType.Item,
                CategoryType.Monster
            )
            capabilities = getChipsFromCategories(categoriesType)
            selectChip(capabilities, 0)
            capabilities.forEachIndexed { index, chip ->
                chip.setOnClickListener {
                    selectChip(capabilities, index)
                    searchViewModel.filterByCategoryType(categoriesType[index])
                    binding.searchView.smoothScrollToPosition(0)
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

    private fun setSearchList() {
        binding.searchView.layoutManager = LinearLayoutManager(context)
        searchListAdapter = SearchAdapter()
        binding.searchView.adapter = searchListAdapter
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        val searchInput = binding.searchInput.query.toString()
        searchViewModel.searchNewResults(searchInput)
        searchViewModel.addElementToHistory(searchInput)
        closeHistoryAndClearFocus()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    fun closeHistoryAndClearFocus() {
        binding.searchInput.clearFocus()
        binding.historyContent.visibility = View.GONE
        binding.closeHistoryBtn.visibility = View.GONE
        selectChip(capabilities, 0)
    }

    private fun openHistory() {
        binding.historyContent.visibility = View.VISIBLE
        binding.closeHistoryBtn.visibility = View.VISIBLE
    }

}