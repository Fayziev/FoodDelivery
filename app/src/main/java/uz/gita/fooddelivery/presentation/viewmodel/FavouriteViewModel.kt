package uz.gita.fooddelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.fooddelivery.data.FoodData

interface FavouriteViewModel {

    val favouriteLiveData: LiveData<List<FoodData>>
    fun increaseCount(id: Long)
    fun decreaseCount(id: Long)
    fun getAllFavourite()
}