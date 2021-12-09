package uz.gita.fooddelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.fooddelivery.data.LocationData

interface TypeViewModel {

    fun getCategories(): List<Long>
//    fun getLocations(): List<LocationData>
}