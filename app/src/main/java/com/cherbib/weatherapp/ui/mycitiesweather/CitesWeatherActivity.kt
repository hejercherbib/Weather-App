package com.cherbib.weatherapp.ui.mycitiesweather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cherbib.weatherapp.databinding.ActivityAddCityBinding
import com.cherbib.weatherapp.ui.mycitiesweather.addcity.AddCityFragment

class CitesWeatherActivity : AppCompatActivity() {
    private lateinit var weatherAdapter: WeatherAdapter

    private var _binding: ActivityAddCityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CityWeatherViewModel by lazy {
        val activity = requireNotNull(this)
        ViewModelProvider(this, CityWeatherViewModel.Factory(activity.application))
            .get(CityWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddCity.setOnClickListener {
            addCity()
        }
        initializeRecycleView()
    }

    fun initializeRecycleView() {
        weatherAdapter = WeatherAdapter()
        binding.rcvMyCities.adapter = weatherAdapter
        binding.rcvMyCities.layoutManager = LinearLayoutManager(this)
        viewModel.weathers.observe(this) { cityWeather ->
            // Update the cached copy of the words in the adapter.
            cityWeather.let {
                Log.i("featched weathers=", cityWeather.toString())
                weatherAdapter.submitList(it)
            }
        }
    }
    fun addCity() {
        val dialogFragment = AddCityFragment()

        val ft = supportFragmentManager.beginTransaction()
        val prev = getSupportFragmentManager().findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        dialogFragment.show(ft, "dialog")
    }

    companion object {
        const val TAG = "AddCityActivity"
    }
}
