package com.weightwatchers.ww_exercise_01.base

abstract class ViewState {
    object Loading : ViewState()
    data class Error(val error: String?, val errorResource:Int? = null) : ViewState()
    abstract class Loaded<out T>(val result: T) : ViewState()
    object Empty : ViewState()
}