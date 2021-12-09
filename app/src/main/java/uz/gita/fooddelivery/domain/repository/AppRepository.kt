package uz.gita.fooddelivery.domain.repository

import uz.gita.fooddelivery.data.AdsData
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.data.LocationData
import uz.gita.fooddelivery.data.TabData
import uz.gita.fooddelivery.data.enums.StartScreenEnum

interface AppRepository {
    val adsData: List<AdsData>
    val foodsData: List<FoodData>
    val popularData: List<FoodData>
    val locationsData: List<LocationData>
    val favouriteList: List<FoodData>
    val tabList:List<TabData>
    val categoryFood:List<Long>



    fun getStartScreen(): StartScreenEnum

    fun successLoadListener(block: () -> Unit)
    fun getAds()
    fun getLocationData()
    fun getAllFoods()
    fun loadTabData()
    fun addFavouriteItem(pos: Long)
    fun deleteFavouriteItem(pos: Long)
    fun increaseCount(id: Long)
    fun decreaseCount(id: Long)
    fun setChangeCountListener(f: () -> Unit)
    fun getLocationByType(type : Long) : List<LocationData>

}

