package com.cherbib.weatherapp.ui.mycitiesweather.addcity

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.cherbib.weatherapp.R
import com.cherbib.weatherapp.data.domain.City

class SuggestionCityAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    cities: List<City>
) :
    ArrayAdapter<City>(mContext, mLayoutResourceId, cities) {
    private val city: MutableList<City> = ArrayList(cities)
    private var allCities: List<City> = ArrayList(cities)

    override fun getCount(): Int {
        return city.size
    }
    override fun getItem(position: Int): City {
        return city[position]
    }
    override fun getItemId(position: Int): Long {
        return city[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = (mContext as Activity).layoutInflater
            convertView = inflater.inflate(mLayoutResourceId, parent, false)
        }
        try {
            val city: City = getItem(position)
            val cityAutoCompleteView = convertView!!.findViewById<View>(R.id.txv_city_name) as TextView
            cityAutoCompleteView.text = city.city
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }
}
