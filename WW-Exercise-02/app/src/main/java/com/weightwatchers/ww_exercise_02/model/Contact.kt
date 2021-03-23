package com.weightwatchers.ww_exercise_02.model

import android.os.Parcelable

data class Contact (
    val name: String,
    val phoneNumber: String
) : Parcelable {

    companion object {
        //TODO Implement the following method:
        fun isValidNumber(number: String) : Boolean {
            return false
        }
    }

}