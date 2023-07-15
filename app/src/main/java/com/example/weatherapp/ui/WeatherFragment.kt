package com.example.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.viewmodel.GlobalViewModel

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val globalViewModel by activityViewModels<GlobalViewModel>()

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
            startAnimation()
        }
    }

    private fun FragmentWeatherBinding.startAnimation() {
        header2.post {
            val heightToAnimate = header2.height
            this.header2.animate().translationY(-heightToAnimate.toFloat()).setDuration(2000).start()
        }
    }

}