package com.weightwatchers.ww_exercise_01.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weightwatchers.ww_exercise_01.utils.coroutines.ContextProviders
import com.weightwatchers.ww_exercise_01.utils.network.ApplicationException
import com.weightwatchers.ww_exercise_01.utils.network.ErrorType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val contextProvider: ContextProviders) : ViewModel() {

    internal val internalState = MutableLiveData<ViewState>()

    val state: LiveData<ViewState> = internalState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handelError(throwable)
    }

    fun launchBlock(block: suspend CoroutineScope.() -> Unit) {
        internalState.value = ViewState.Loading
        viewModelScope.launch(contextProvider.Main + coroutineExceptionHandler) {
            block.invoke(this)
        }
    }

    private fun handelError(throwable: Throwable) {
        if (throwable is ApplicationException) {
            internalState.value = ViewState.Error(throwable.errorMessage, throwable.errorMessageRes)
            // handle error according to its type
            when (throwable.type) {
                ErrorType.Network.Unauthorized -> { }
                ErrorType.Network.ResourceNotFound -> {}
                ErrorType.Network.Unexpected -> {}
                ErrorType.Network.NoInternetConnection -> {}
                else -> {}
            }
        }
    }
}