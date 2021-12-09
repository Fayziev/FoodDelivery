package uz.gita.fooddelivery.domain.repository.impl

import com.google.firebase.firestore.FirebaseFirestore
import uz.gita.fooddelivery.data.AdsData
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.data.LocationData
import uz.gita.fooddelivery.data.TabData
import uz.gita.fooddelivery.data.enums.StartScreenEnum
import uz.gita.fooddelivery.database.AppDatabase
import uz.gita.fooddelivery.database.FoodEntity
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.shared.LocalStorage
import uz.gita.fooddelivery.tools.myLog
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val storage: LocalStorage,
    private val firestore: FirebaseFirestore
) : AppRepository {
    private val dao = AppDatabase.getDatabase().getDao()
    override val favouriteList: List<FoodData> get() = foodsData.filter { it.isSelected }
    override val locationsData: ArrayList<LocationData> = ArrayList()
    override val adsData = ArrayList<AdsData>()
    override val foodsData = ArrayList<FoodData>()
    override val popularData: List<FoodData> get() = foodsData.filter { it.isFavourite }
    override val categoryFood: ArrayList<Long> = ArrayList()
    override val tabList = ArrayList<TabData>()
    private var errorLoadListener: (() -> Unit)? = null
    private var changeCountListener: (() -> Unit)? = null

    private var successLoadListener: (() -> Unit)? = null

    override fun getStartScreen(): StartScreenEnum = storage.startScreen

    override fun getAds() {
        firestore.collection("ads")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    val id = document["id"] as Long
                    val imageURL = document["imageURL"] as String
                    adsData.add(AdsData(id, imageURL))
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
                myLog(it.message.toString())
                errorLoadListener?.invoke()
            }
    }

    override fun getLocationData() {
        firestore.collection("locations")
            .get()
            .addOnSuccessListener { result ->
                result.onEach { document ->
                    val id = document["id"] as Long
                    val description = document["description"] as String
                    val latitude = document["latitude"] as Double
                    val longitude = document["longitude"] as Double
                    val time = document["time"] as String
                    val name = document["name"] as String
                    val type = document["type"] as Long
                    val data = LocationData(id, name, description, time, latitude, longitude, type)
                    locationsData.add(data)
                    myLog("typeLocation = $type")
                    categoryFood.add(data.type)
                }
                successLoadListener?.invoke()
            }.addOnFailureListener {
                myLog(it.message.toString())
                errorLoadListener?.invoke()
            }
    }

    override fun loadTabData() {
        tabList.add(TabData("TASHKENT", 1))
        tabList.add(TabData("FARG'ONA", 2))
        tabList.add(TabData("QASHQADARYO", 3))
        tabList.add(TabData("ANDIJON", 4))
        tabList.add(TabData("QO'QON", 5))
        tabList.add(TabData("NAMANGAN", 6))
        tabList.add(TabData("SAMARQAND", 7))
    }

    override fun successLoadListener(block: () -> Unit) {
        successLoadListener = block
    }

    override fun getAllFoods() {
        firestore.collection("foods")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    document.data.apply {
                        val id = this["id"] as Long
                        val name = this["name"] as String
                        val cost = this["cost"] as Long
                        val imageURL = this["imageURL"] as String
                        val type = this["type"] as Long
                        val isFavourite = this["isFavourite"] as Boolean
                        myLog("type = $type")
                        foodsData.add(FoodData(id, name, cost, imageURL, type, isFavourite, 0))
                        dao.insert(FoodEntity(0, name, cost, imageURL, type, isFavourite, 0))
                    }
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
                myLog(it.message.toString())
                errorLoadListener?.invoke()
            }
    }


    override fun addFavouriteItem(pos: Long) {
        foodsData.forEach {
            if (it.id == pos) {
                it.isSelected = true
                dao.update(
                    FoodEntity(
                        pos, it.name, it.cost, it.imageURL, it.type, it.isFavourite, it.count, true
                    )
                )
            }
        }
    }

    override fun deleteFavouriteItem(pos: Long) {
        foodsData.forEach {
            if (it.id == pos) {
                it.isSelected = false
                dao.update(
                    FoodEntity(
                        pos, it.name, it.cost, it.imageURL, it.type, it.isFavourite, it.count, false
                    )
                )
            }
        }
    }

    override fun increaseCount(id: Long) {
        foodsData.forEach {
            if (it.id == id) {
                it.count++
            }
            changeCountListener?.invoke()
        }
    }

    override fun decreaseCount(id: Long) {
        foodsData.forEach {
            if (it.id == id) {
                it.count--
            }
            changeCountListener?.invoke()
        }
    }

    override fun setChangeCountListener(f: () -> Unit) {
        changeCountListener = f
    }

    override fun getLocationByType(type: Long): List<LocationData> = locationsData.filter { it.type == type }
}