package uz.gita.fooddelivery.data

import uz.gita.fooddelivery.R

enum class CategoryEnum {
    EVOS, DEFAULT
}

fun CategoryEnum.getDrawableResources(): Int {
    return when (this) {
        CategoryEnum.EVOS -> R.drawable.location
        else -> R.drawable.location
    }
}