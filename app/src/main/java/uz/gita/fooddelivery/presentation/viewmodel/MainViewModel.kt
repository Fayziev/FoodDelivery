package uz.gita.fooddelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.fooddelivery.data.AdsData
import uz.gita.fooddelivery.data.FoodData

interface MainViewModel {

    val popularListLiveData: LiveData<List<FoodData>>
    val adsData:LiveData<List<AdsData>>
    fun getPopular()
    fun adsData()
    fun increaseCount(id: Long)
    fun decreaseCount(id: Long)
}