package com.weightwatchers.ww_exercise_01.data.remote.meals

import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
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

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockWebServer.enqueue(
                MockResponse()
                        .setBody(dummyMealsJson)
                        .setResponseCode(200)
        )
    }

    @Test
    fun `get meals load all the meals from the json file as expected`() {

        val meals = runBlocking{
            albumsService.getMeals().body()
        }

        Assert.assertEquals(meals?.size, 8)
    }
}