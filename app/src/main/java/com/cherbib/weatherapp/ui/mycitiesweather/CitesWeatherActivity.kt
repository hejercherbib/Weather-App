package com.cherbib.weatherapp.ui.mycitiesweather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cherbib.weatherapp.databinding.ActivityAddCityBinding
import com.cherbib.weatherapp.ui.addcity.AddCityFragment

class CitesWeatherActivity : AppCompatActivity() {
    private lateinit var cityWeatherAdapter: CityWeatherAdapter

    private var _binding: ActivityAddCityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CityWeatherViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, CityWeatherViewModel.Factory(activity.application))
            .get(CityWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.weathers.observe(this) { cityWeather ->
            // Update the cached copy of the words in the adapter.
            cityWeather.let {
                Log.i("featched weathers=", cityWeather.toString())
                cityWeatherAdapter.submitList(it)
            }
        }
        binding.btnAddCity.setOnClickListener {
            addCity()
        }
        initializeRecycleView()
    }

    fun initializeRecycleView() {
        cityWeatherAdapter = CityWeatherAdapter()
        binding.rcvMyCities.adapter = cityWeatherAdapter
        binding.rcvMyCities.layoutManager = LinearLayoutManager(this)
        // Todo submit fetched list
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
