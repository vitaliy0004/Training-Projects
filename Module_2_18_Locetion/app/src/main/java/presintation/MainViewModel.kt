package presintation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_2_18_locetion.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import data.GetLocation
import data.LocationStructure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.jar.Manifest
import kotlin.coroutines.coroutineContext

class MainViewModel() : ViewModel() {
    private var locationListener: LocationSource.OnLocationChangedListener? = null
    var map: GoogleMap? = null

    val places : List<LocationStructure> by lazy {
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