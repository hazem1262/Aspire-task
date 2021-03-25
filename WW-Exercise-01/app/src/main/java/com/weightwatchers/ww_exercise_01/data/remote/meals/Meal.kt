package com.weightwatchers.ww_exercise_01.data.remote.meals


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("filter")
    val filter: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("title")
    val title: String?
){
    // beautify the returned tags from the api
    fun getFormattedTags():String{
        var tags = arrayListOf<String>()
        filter?.split(',')?.onEach {
            var tag: String = it.replaceBeforeLast(':',"")
            tag = tag.replaceAfterLast("\\","")
            tag = tag.replace("\\","")
            tag = tag.replace(":","")
            tags.add(tag)
        }

        return tags.joinToString(separator = ", ") { it }
    }
}