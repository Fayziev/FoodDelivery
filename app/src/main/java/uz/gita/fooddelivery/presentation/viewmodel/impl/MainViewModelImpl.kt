package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.fooddelivery.data.AdsData
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(), MainViewModel {

    override val popularListLiveData = MutableLiveData<List<FoodData>>()
    override val adsData = MutableLiveData<List<AdsData>>()

    init {
        repository.setChangeCountListener {
            getPopular()
        }
    }

    override fun getPopular() {
        popularListLiveData.postValue(repository.popularData)
    }

    override fun adsData() {
        adsData.postValue(repository.adsData)
    }

    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
    }
}