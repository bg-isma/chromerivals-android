package com.ismita.chromerivals.presenntation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.ActivityMainNavigationBinding
import com.ismita.chromerivals.presenntation.ui.home.HomeFragment
import com.ismita.chromerivals.presenntation.ui.pedia.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import com.ismita.chromerivals.utils.CheckNetworkConnection


@AndroidEntryPoint
class MainNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainNavigationBinding

    private lateinit var appFragments: List<Fragment>
    private lateinit var active: Fragment

    private lateinit var noInternetView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callNetworkConnection()

        binding.navView.itemIconTintList = null
        noInternetView = LayoutInflater.from(this).inflate(R.layout.no_internet_card, binding.container, false)
        binding.container.addView(noInternetView)

        appFragments = listOf(
            HomeFragment(),
            // LootingFragment(),
            SearchFragment()
        )
        active = appFragments[0]

        setUpFragmentAtStart()
        binding.navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> hideAndOpenFragment(0)
                // R.id.navigation_looting -> hideAndOpenFragment(1)
                R.id.navigation_dashboard -> hideAndOpenFragment(1)
            }
            false
        }

    }

    private fun hideAndOpenFragment(index: Int) {
        active.view?.visibility = View.GONE
        supportFragmentManager
            .beginTransaction()
            .hide(active)
            .show(appFragments[index])
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
        appFragments[index].view?.visibility = View.VISIBLE
        active = appFragments[index]
    }

    private fun setUpFragmentAtStart() {
        val supportManager = supportFragmentManager.beginTransaction()
        appFragments.forEachIndexed { index, fragment ->
            if (index == 0) supportManager
                .add(R.id.nav_host_fragment_activity_main_navigation, fragment, index.toString())
            else supportManager
                .add(R.id.nav_host_fragment_activity_main_navigation, fragment, index.toString())
                .hide(fragment)
        }
        supportManager.commit()
    }

    private fun callNetworkConnection() {
        val checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (isConnected) noInternetView.visibility = View.GONE
            else noInternetView.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        when (active) {
            is HomeFragment -> hideAndOpenFragment(1)
            is SearchFragment -> hideAndOpenFragment(0)
        }
    }

}