package uz.gita.fooddelivery.database

import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: FoodEntity)

    @Update
    fun update(data: FoodEntity)

    @Delete
    fun delete(data: FoodEntity)

    @Query("SELECT * FROM FoodEntity")
    fun getDataAll(): List<FoodEntity>

}