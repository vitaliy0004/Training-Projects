package presintation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.module_2_18_locetion.R
import com.example.module_2_18_locetion.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment() : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    val binding get() = _binding!!
    val viewModel: MainViewModel by viewModels()

    lateinit var fusedClient: FusedLocationProviderClient
    @SuppressLint("MissingPermission")
    fun startLocation() {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, INTERVAL_MILLIS)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(MIN_MILLIS)
            .setMaxUpdateDelayMillis(MAX_MILLIS)
            .build()
        fusedClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedClient.requestLocationUpdates(
            request,
            viewModel.locationCallBack,
            Looper.getMainLooper()
        )
    }

    private fun checkPermissions() {
        if(REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(this.requireContext(),permission) == PackageManager.PERMISSION_GRANTED
            })
            startLocation()
        else launcher.launch(REQUIRED_PERMISSIONS)
    }


    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map.values.isNotEmpty() && map.values.all { it }) {
            startLocation()
        }

    }
    override fun onStart() {
        super.onStart()
        checkPermissions()
    }

    @SuppressLint("MissingPermission")
    val callback = OnMapReadyCallback { googleMap ->
        viewModel.map = googleMap
        with(googleMap.uiSettings) {
            this.isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }
        viewModel.map?.isMyLocationEnabled = true
            viewModel.map?.setInfoWindowAdapter(MarkerInfo(requireContext()))
            viewModel.places.forEach { place ->
                val latLng = LatLng(place.location_lat, place.location_lag)
                val marker = viewModel.map?.addMarker(
                    MarkerOptions()
                        .title(place.name)
                        .position(latLng)
                )
                marker?.tag = place
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        private val INTERVAL_MILLIS = 1000L
        private val MIN_MILLIS = 500L
        private val MAX_MILLIS = 1000L
        private val REQUIRED_PERMISSIONS: Array<String> =  arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}




