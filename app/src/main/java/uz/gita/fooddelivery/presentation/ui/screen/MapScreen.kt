package uz.gita.fooddelivery.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.data.LocationData
import uz.gita.fooddelivery.data.getDrawableResources
import uz.gita.fooddelivery.presentation.ui.dialog.InfoDialog
import uz.gita.fooddelivery.presentation.viewmodel.MapViewModel
import uz.gita.fooddelivery.presentation.viewmodel.impl.MapViewModelImpl

@AndroidEntryPoint
class MapScreen : Fragment(R.layout.screen_map), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var data: LocationData
    private val viewModel: MapViewModel by viewModels<MapViewModelImpl>()
    private val list = ArrayList<LocationData>()
    private lateinit var sampleList: List<LocationData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
        data = requireActivity().intent.getSerializableExtra("data") as LocationData
        sampleList = viewModel.getLocations()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        loadData(sampleList)
        addMarkers()
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isRotateGesturesEnabled = true
        mMap.mapType = 5
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(data.latitude, data.longitude), 18f))
        mMap.setOnMarkerClickListener { marker ->
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 18f))
            val dialog = InfoDialog()
            val bundle = Bundle()
            bundle.putSerializable("data", findLocationByLatLng(marker.position))
            dialog.arguments = bundle
            dialog.show(childFragmentManager, "dialog")

            return@setOnMarkerClickListener true
        }
    }

    private fun findLocationByLatLng(position: LatLng): LocationData {
        return list.first { it.latitude == position.latitude && it.longitude == position.longitude }
    }

    private fun addMarkers() {
        for (element in list) {
            val latLng = LatLng(element.latitude, element.longitude)
            mMap.addMarker(
                MarkerOptions().position(latLng)
                    .title(element.name)
                    .icon(BitmapDescriptorFactory.fromResource(element.category.getDrawableResources()))
            )
        }

    }

    private fun loadData(sampleList: List<LocationData>) {
        list.addAll(sampleList)
    }
}