import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.cherbib.weatherapp.R
import com.cherbib.weatherapp.data.domain.City

public class SuggestionCityAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val allCities: List<City>) :
    ArrayAdapter<City>(context, layoutResource, allCities),
    Filterable {
    private var mAllCities: List<City> = allCities

    override fun getCount(): Int {
        return mAllCities.size
    }

    override fun getItem(p0: Int): City? {
        return mAllCities.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // Or just return p0
        return mAllCities.get(p0).id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = (context as Activity).layoutInflater
            convertView = inflater.inflate(layoutResource, parent, false)
        }
        try {
            val city: City? = getItem(position)
            val cityAutoCompleteView = convertView!!.findViewById<View>(R.id.txv_city_name) as TextView
            cityAutoCompleteView.text = city?.city
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                mAllCities = filterResults.values as List<City>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.uppercase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString == null || queryString.isEmpty()) {
                    allCities
                } else {
                    allCities.filter {
                        it.city.uppercase().contains(queryString)
                    }
                }
                return filterResults
            }
        }
    }
}
