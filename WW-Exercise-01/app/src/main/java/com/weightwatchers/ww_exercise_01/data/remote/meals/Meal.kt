package com.weightwatchers.ww_exercise_01.data.remote.meals


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("filter")
    val filter: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("title")
    val title: String?
)