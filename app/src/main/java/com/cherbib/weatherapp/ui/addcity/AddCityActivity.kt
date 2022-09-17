package com.cherbib.weatherapp.ui.addcity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cherbib.weatherapp.R

class AddCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddCityFragment.newInstance())
                .commitNow()
        }
    }
}
