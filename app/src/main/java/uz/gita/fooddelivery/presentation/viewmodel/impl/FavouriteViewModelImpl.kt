package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.FavouriteViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), FavouriteViewModel {
    override val favouriteLiveData = MutableLiveData<List<FoodData>>()
    init {
        appRepository.setChangeCountListener {
            getAllFavourite()
        }
    }

    override fun increaseCount(id: Long) {
        appRepository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        appRepository.decreaseCount(id)
    }

    override fun getAllFavourite() {
        favouriteLiveData.postValue(appRepository.favouriteList)
    }
}