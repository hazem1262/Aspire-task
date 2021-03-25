package com.weightwatchers.ww_exercise_01.application

import android.app.Application
import com.weightwatchers.ww_exercise_01.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppInstance: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            fragmentFactory()
            androidContext(this@AppInstance)
            modules(
                    listOf(
                            appModule,
                            repositoryModule,
                            retrofitModule,
                            apiModule,
                            fragmentModule
                    )
            )
        }
    }
}