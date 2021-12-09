package uz.gita.fooddelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.fooddelivery.data.FoodData

interface InfoViewModel {
    val allFavouriteLiveData: LiveData<FoodData>

    fun increaseCount(id: Long)
    fun decreaseCount(id: Long)
    fun getFavourites(): List<FoodData>
    fun addFavourite(pos: Long)
    fun deleteFromFavourite(pos: Long)
    fun getState(): Boolean
}