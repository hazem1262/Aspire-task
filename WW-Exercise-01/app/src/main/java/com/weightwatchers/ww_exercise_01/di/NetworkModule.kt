package com.weightwatchers.ww_exercise_01.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.weightwatchers.ww_exercise_01.data.remote.meals.MealsService
import com.weightwatchers.ww_exercise_01.data.repositories.MealsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single {
        MealsRepository(
                get(),
                get()
        )
    }
}

val apiModule = module {
    fun provideMealsApi(retrofit: Retrofit): MealsService {
        return retrofit.create(MealsService::class.java)
    }
    single { provideMealsApi(get()) }
}

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(logging)
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://www.weightwatchers.com/")
                .addConverterFactory(GsonConverterFactory.create(factory))
                .client(client)
                .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}