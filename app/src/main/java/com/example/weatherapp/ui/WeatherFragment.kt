package com.example.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.viewmodel.GlobalViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val globalViewModel by activityViewModels<GlobalViewModel>()

    private val tabData = mutableListOf(
        "Weather"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with((requireActivity().application as WeatherApplication).applicationComponent) {
            inject(this@WeatherFragment)
            inject(globalViewModel)
        }
        lifecycle.addObserver(globalViewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<FragmentWeatherBinding>(view)?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = globalViewModel
            setUpViewPager()
            setUpTabLayout()
        }
    }

    private fun FragmentWeatherBinding.setUpViewPager() {
        val viewPagerAdapter = object : FragmentStateAdapter(this@WeatherFragment) {
            override fun getItemCount(): Int = tabData.size
            override fun createFragment(position: Int): Fragment = MultipleDayWeatherFragment()
        }
        viewPager.adapter = viewPagerAdapter
    }

    private val onTabSelectedCallback = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            
        }

    }

    private fun FragmentWeatherBinding.setUpTabLayout() {
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tabData.getOrNull(position)?.let {
                tab.text = it
            }
        }
        tabLayout.addOnTabSelectedListener(onTabSelectedCallback)
        tabLayoutMediator.attach()
    }

}