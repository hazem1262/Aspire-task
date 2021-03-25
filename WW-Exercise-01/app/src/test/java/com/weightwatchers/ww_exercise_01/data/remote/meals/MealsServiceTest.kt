package com.weightwatchers.ww_exercise_01.data.remote.meals

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsServiceTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val dummyMealsJson = ClassLoader.getSystemResource("meals.json").readText()

    private val albumsService by lazy {
        retrofit.create(MealsService::class.java)
    }

    @Test
    fun getAlbums() {
        mockWebServer.enqueue(
                MockResponse()
                        .setBody(dummyMealsJson)
                        .setResponseCode(200)
        )

        val meals = runBlocking{
            albumsService.getAlbums().body()
        }
        Assert.assertEquals(meals?.size, 8)
    }
}