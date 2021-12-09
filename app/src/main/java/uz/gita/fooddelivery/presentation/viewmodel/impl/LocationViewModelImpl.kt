package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.fooddelivery.data.LocationData
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.LocationViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(), LocationViewModel {
    override val allLocationData = MutableLiveData<List<LocationData>>()


    override fun getAllLocationData() {
        repository.getLocationData()
        allLocationData.value = repository.locationsData
    }
}