package presintation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.module_2_18_locetion.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import data.LocationStructure

class MarkerInfo(
    private val context: Context
): GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        val place = marker.tag as? LocationStructure ?: return null
        val view = LayoutInflater.from(context).inflate(R.layout.marker_info_contents, null)
        view.findViewById<TextView>(R.id.text_name).text = place.name
        view.findViewById<TextView>(R.id.text_info).text = place.information
        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
      return null
    }
}