package com.weightwatchers.ww_exercise_01.utils.network

import com.google.gson.JsonObject

class ApplicationException(
        val type: ErrorType,
        val errorMessage: String? = null,
        val errorMessageRes: Int? = null,
        val throwable: Throwable? = null,
        val tag:String = "",
        val extra: JsonObject? = null
) : RuntimeException()

sealed class ErrorType {
    sealed class Network(Code: Int) : ErrorType() {
        object Unauthorized : Network(401)
        object ResourceNotFound : Network(404)
        object Unexpected : Network(-1)
        object NoInternetConnection : Network(-2)
    }

    object Unexpected : ErrorType()
    object UserError : ErrorType()

}
