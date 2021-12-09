package uz.gita.fooddelivery.presentation.viewmodel

import uz.gita.fooddelivery.data.LocationData

interface MapViewModel {

    fun getLocations(): List<LocationData>
}