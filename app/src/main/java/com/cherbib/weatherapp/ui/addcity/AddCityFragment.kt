package com.cherbib.weatherapp.ui.addcity

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cherbib.weatherapp.R
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.databinding.DialogFragmentAddCityBinding

class AddCityFragment : DialogFragment() {
    private var _binding: DialogFragmentAddCityBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: CityViewModel
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        _binding = DialogFragmentAddCityBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAutoCompletCities()
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
            val cityAdapter = CityAdapter(it, R.layout.item_city, cities)
            autoCompleteTxv.threshold = 2
            autoCompleteTxv.setAdapter(cityAdapter)

            autoCompleteTxv.setOnItemClickListener(
                { adapterView, view, position, l ->
                }
            )
        }
    }
    
    fun initCitiesRcv(){

    }
}
