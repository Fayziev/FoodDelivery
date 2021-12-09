package uz.gita.fooddelivery.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.fooddelivery.app.App

@Database(entities = [FoodEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): FoodDao

    companion object {
        private lateinit var instance: AppDatabase
        fun getDatabase(): AppDatabase {
            if (!Companion::instance.isInitialized) {
                instance = Room.databaseBuilder(App.instance, AppDatabase::class.java, "EVOSEntity")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}