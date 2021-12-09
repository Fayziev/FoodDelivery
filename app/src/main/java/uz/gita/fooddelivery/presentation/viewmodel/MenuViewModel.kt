package uz.gita.fooddelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.fooddelivery.data.FoodData

interface MenuViewModel {

    val allFoodLiveData: LiveData<List<FoodData>>
    val categoryFoodLiveData: LiveData<List<Long>>
    fun getAllFoods()
    fun increaseCount(id: Long)
    fun decreaseCount(id: Long)
}