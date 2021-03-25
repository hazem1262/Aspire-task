package com.weightwatchers.ww_exercise_01.ui.main.meal

import com.weightwatchers.ww_exercise_01.base.ViewState
import com.weightwatchers.ww_exercise_01.data.remote.meals.Meal

class MealsViewState {
    data class Loaded(val meals: List<Meal>) : ViewState.Loaded<List<Meal>>(meals)
}