package uz.gita.fooddelivery.data

import java.io.Serializable

data class LocationData(
    val id: Long,
    val name: String,
    val description: String,
    val time: String,
    val latitude: Double,
    val longitude: Double,
    val type: Long,
    val category : CategoryEnum = CategoryEnum.DEFAULT

):Serializable

enum class LocationEnum(val pos: Int) {
    TASHKENT(1),
    FARGONA(2),
    QASHQADARYO(3),
    ANDIJON(4),
    QOQON(5),
    NAMANGAN(6),
    SAMARQAND(7)
}