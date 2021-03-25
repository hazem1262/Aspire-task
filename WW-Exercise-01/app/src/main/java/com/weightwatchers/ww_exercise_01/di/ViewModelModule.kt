package com.weightwatchers.ww_exercise_01.di

import com.weightwatchers.ww_exercise_01.ui.main.meal.MealsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MealsViewModel(get(), get())
    }
}