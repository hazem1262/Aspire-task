package com.weightwatchers.ww_exercise_01.di

import com.weightwatchers.ww_exercise_01.utils.coroutines.ContextProviders
import org.koin.dsl.module

val appModule = module {
    single {
        ContextProviders.getInstance()
    }
}

val fragmentModule = module{

}