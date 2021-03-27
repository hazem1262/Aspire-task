package com.weightwatchers.ww_exercise_01.ui.main.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weightwatchers.ww_exercise_01.base.BaseViewModel
import com.weightwatchers.ww_exercise_01.base.ViewState
import com.weightwatchers.ww_exercise_01.data.remote.meals.Meal
import com.weightwatchers.ww_exercise_01.data.repositories.MealsRepository
import com.weightwatchers.ww_exercise_01.utils.coroutines.ContextProviders

class MealsViewModel(
        contextProvider: ContextProviders,
        private val mealsRepository: MealsRepository
) : BaseViewModel(contextProvider) {

    private val mealsLiveData:MutableLiveData<List<Meal>> by lazy {
        MutableLiveData<List<Meal>>().also {
            loadMeals()
        }
    }

    // use data binding to bind the meals list to the recycler view
    fun getMeals():LiveData<List<Meal>>{
        return mealsLiveData
    }

    fun loadMeals() {
        launchBlock{
            val meals = mealsRepository.getMeals()
            mealsLiveData.postValue(meals)
            internalState.postValue(
                    if (meals.isNullOrEmpty()) ViewState.Empty else MealsViewState.Loaded(meals)
            )
        }
    }
}