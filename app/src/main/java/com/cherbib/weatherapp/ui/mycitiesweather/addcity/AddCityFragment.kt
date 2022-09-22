package com.cherbib.weatherapp.ui.mycitiesweather.addcity

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cherbib.weatherapp.R
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.databinding.DialogFragmentAddCityBinding
import com.cherbib.weatherapp.ui.mycitiesweather.CityWeatherViewModel

class AddCityFragment : DialogFragment(), AdapterOnClick {
    private lateinit var cityAdapter: SavedCityAdapter
    private var _binding: DialogFragmentAddCityBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: CityWeatherViewModel
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CityWeatherViewModel::class.java)
        _binding = DialogFragmentAddCityBinding.inflate(inflater, container, false)
        initSavedCitiesRcv()
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        initAutoCompletCities()
        binding.btnBack.setOnClickListener {
            this.dismiss()
        }
    }
    fun initAutoCompletCities() {
        viewModel.allCities.observe(viewLifecycleOwner, {
            setupSearchField(binding.autoCompleteTextViewCities, it)
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_NoActionBar)
    }
    private fun setupSearchField(autoCompleteTxv: AutoCompleteTextView, cities: List<City>) {
        activity?.let {
            val suggestionCityAdapter = SuggestionCityAdapter(it, R.layout.item_city_suggestion, cities)
            autoCompleteTxv.threshold = 2
            autoCompleteTxv.setAdapter(suggestionCityAdapter)

            autoCompleteTxv.setOnItemClickListener { adapterView, view, position, l ->
                val item = adapterView.getItemAtPosition(position)
                if (item is City) {
                    viewModel.insertCity(item)
                }
                autoCompleteTxv.setText("")
            }
        }
    }

    fun initSavedCitiesRcv() {
        cityAdapter = SavedCityAdapter(this)
        binding.rcvMyCities.adapter = cityAdapter
        binding.rcvMyCities.layoutManager = LinearLayoutManager(context)
        viewModel.allSavedCities.observe(viewLifecycleOwner, {
            cityAdapter.submitList(it)
        })
    }

    override fun onDeleteClick(item: City) {
        viewModel.deleteCity(item)
    }
}
