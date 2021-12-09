package uz.gita.fooddelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.fooddelivery.data.LocationData

interface LocationViewModel {

    val allLocationData:LiveData<List<LocationData>>
    fun getAllLocationData()
}