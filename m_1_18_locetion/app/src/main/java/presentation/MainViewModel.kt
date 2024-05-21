package presentation

import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.LatLng
import data.GetLocation
import data.LocationStructure

class MainViewModel : ViewModel() {
    private var locationListener: LocationSource.OnLocationChangedListener? = null
    var map: GoogleMap? = null

    val places: List<LocationStructure> by lazy {
        GetLocation().getObjectList()
    }

    var start = true

    val locationCallBack = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location ->
                locationListener?.onLocationChanged(location)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    LatLng(location.latitude, location.longitude),
                    18f
                )
                if (start) {
                    start = false
                    map?.moveCamera(cameraUpdate)
                    /*if (needAnimateCamera){
                        map?.animateCamera(cameraUpdate)
                    }
                    else{
                        needAnimateCamera = true
                        map?.moveCamera(cameraUpdate)
                    }*/
                    //этот код для слижения за точкой

                }
            }
        }
    }
}