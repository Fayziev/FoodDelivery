package uz.gita.fooddelivery.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val cost: Long,
    val imageURL: String,
    val type: Long,
    val isFavourite: Boolean,
    var count: Long ,
    var isSelected: Boolean=false
)