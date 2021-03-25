package com.weightwatchers.ww_exercise_01.data.repositories

import com.weightwatchers.ww_exercise_01.base.BaseRepository
import com.weightwatchers.ww_exercise_01.data.remote.meals.Meal
import com.weightwatchers.ww_exercise_01.data.remote.meals.MealsService
import com.weightwatchers.ww_exercise_01.utils.coroutines.ContextProviders

class MealsRepository(
        private val contextProviders: ContextProviders,
        private val mealsService: MealsService
): BaseRepository(contextProviders) {
    suspend fun getMeals():List<Meal>?{
        return launchBlock{
            return@launchBlock mealsService.getAlbums()
        }
    }
}