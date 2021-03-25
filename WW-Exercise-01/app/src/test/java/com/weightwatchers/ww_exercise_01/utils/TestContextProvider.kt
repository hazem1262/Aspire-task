package com.weightwatchers.ww_exercise_01.utils

import com.weightwatchers.ww_exercise_01.utils.coroutines.ContextProviders
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ContextProviders() {
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined
    override val IO: CoroutineContext
        get() = Dispatchers.Unconfined
}