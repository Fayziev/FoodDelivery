package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.InfoViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), InfoViewModel {

    override val allFavouriteLiveData = MutableLiveData<FoodData>()

    override fun increaseCount(id: Long) = repository.increaseCount(id)
    override fun decreaseCount(id: Long) = repository.decreaseCount(id)
    override fun getFavourites(): List<FoodData> = repository.favouriteList
    override fun addFavourite(pos: Long) = repository.addFavouriteItem(pos)
    override fun deleteFromFavourite(pos: Long) = repository.deleteFavouriteItem(pos)
    override fun getState(): Boolean = true
}