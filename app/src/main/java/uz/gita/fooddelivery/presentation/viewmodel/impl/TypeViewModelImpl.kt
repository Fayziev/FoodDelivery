package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.fooddelivery.data.LocationData
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.TypeViewModel
import javax.inject.Inject

@HiltViewModel
class TypeViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(), TypeViewModel {
    override fun getCategories(): List<Long> = repository.categoryFood

//    override fun getLocations(): List<LocationData>? =null
}