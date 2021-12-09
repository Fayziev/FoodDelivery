package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.fooddelivery.data.LocationData
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.MapViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), MapViewModel {

    override fun getLocations(): List<LocationData> = appRepository.locationsData
}