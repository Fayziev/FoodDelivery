package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(), SplashViewModel {
    override val openMainLiveData = MutableLiveData<Unit>()
    private var count = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAds()
            repository.getAllFoods()
            repository.getLocationData()
            repository.successLoadListener {
                count++
                if (count == 3) {
                    openMainLiveData.value = Unit
                }
            }
        }
    }
}