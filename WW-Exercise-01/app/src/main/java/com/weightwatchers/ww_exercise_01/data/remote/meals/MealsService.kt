package com.weightwatchers.ww_exercise_01.data.remote.meals

import com.weightwatchers.ww_exercise_01.utils.network.NetworkUtil.EndPoints.MEALS
import retrofit2.Response
import retrofit2.http.GET

interface MealsService {
    @GET(MEALS)
    suspend fun getMeals(): Response<List<Meal>>
}