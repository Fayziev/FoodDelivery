package uz.gita.fooddelivery.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.domain.repository.AppRepository
import uz.gita.fooddelivery.presentation.viewmodel.MenuViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(), MenuViewModel {


    override val allFoodLiveData = MutableLiveData<List<FoodData>>()
    override val categoryFoodLiveData = MutableLiveData<List<Long>>()

    init {
        repository.setChangeCountListener {
            getAllFoods()
        }
    }

    override fun getAllFoods() {
        allFoodLiveData.value = repository.foodsData
        categoryFoodLiveData.value = repository.categoryFood
    }

    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
    }

}