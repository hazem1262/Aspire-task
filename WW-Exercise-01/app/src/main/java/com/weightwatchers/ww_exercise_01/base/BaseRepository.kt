package com.weightwatchers.ww_exercise_01.base

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.weightwatchers.ww_exercise_01.utils.coroutines.ContextProviders
import com.weightwatchers.ww_exercise_01.utils.network.ApplicationException
import com.weightwatchers.ww_exercise_01.utils.network.ErrorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository(private val contextProviders: ContextProviders) {

    private val gSon = Gson()

    // add tag to each request to differentiate between requests in the same page if needed
    suspend fun <T> launchBlock(tag:String = "", block: suspend CoroutineScope.() -> Response<T>): T {
        return withContext(contextProviders.IO) {
            return@withContext handleResult(block.invoke(this), tag = tag)
        }
    }

    private fun <T> handleResult(response: Response<T>, tag:String = ""): T {
        return when (response.code()) {
            in 1..399 -> response.body()!!
            401 -> throw ApplicationException(
                type = ErrorType.Network.Unauthorized,
                errorMessage = getErrorMessage(response),
                tag = tag
            )
            404 -> throw ApplicationException(
                type = ErrorType.Network.ResourceNotFound,
                errorMessage = getErrorMessage(response),
                tag = tag
            )
            else -> throw getApplicationException(response, tag)
        }
    }

    // example of custom error where back end sends extra data with error to be displayed
    private fun <T> getApplicationException(response: Response<T>, tag:String):ApplicationException{
        val errorObject = try {
            val jsonObj = JsonParser().parse(response.errorBody()?.string()).asJsonObject
            jsonObj["error"]
        } catch (e: Exception){
            null
        }
        return ApplicationException(
            extra = errorObject?.asJsonObject?.get("extra")?.asJsonObject,
            type = ErrorType.Network.Unexpected,
            errorMessage = errorObject?.asJsonObject?.get("message")?.toString()?.replace('"',' ', true),
            tag = tag

        )
    }

    // assume base response is the confirmed response with back end
    private fun <T> getErrorMessage(response: Response<T>): String? {
        // todo return the error message from the api response when confirmed with back end developers
        return "Network error"
        /*
        return gSon.fromJson<BaseResponse<*>>(
            response.errorBody()?.string(),
            object : TypeToken<BaseResponse<*>>() {}.type
        ).error?.message*/
    }
}