package uz.gita.fooddelivery.data

import java.io.Serializable

data class FoodData(
    val id: Long,
    val name: String,
    val cost: Long,
    val imageURL: String,
    val type: Long,
    val isFavourite: Boolean,
    var count: Long ,
    var isSelected: Boolean=false
):Serializable

enum class TypeEnum(
    val pos: Int,
    val text: String
) {
    SET(1, "Set"),
    LAVASH(2, "Lavash"),
    SHAURMA(3, "Shaurma"),
    DONAR(4, "Donar"),
    BURGER(5, "Burger"),
    XOTDOG(6, "Xot-dog"),
    DESERTLAR(7, "Desertlar"),
    ICHIMLIKLAR(8, "Ichimliklar"),
    GAZAKLAR(9, "Gazaklar")
}
