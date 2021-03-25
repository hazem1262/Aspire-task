package com.weightwatchers.ww_exercise_01.ui.main.meal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.weightwatchers.ww_exercise_01.base.ViewState
import com.weightwatchers.ww_exercise_01.data.remote.meals.MealsService
import com.weightwatchers.ww_exercise_01.data.repositories.MealsRepository
import com.weightwatchers.ww_exercise_01.utils.TestContextProvider
import io.mockk.MockKAnnotations
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MealsViewModelIntegrationTest{
    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var stateObserver: Observer<ViewState>

    private lateinit var viewModel: MealsViewModel
    lateinit var repository: MealsRepository
    private val contextProvidersTest = TestContextProvider()
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val dummyMealsJson = ClassLoader.getSystemResource("meals.json").readText()

    private val mealsService by lazy {
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
        repository = MealsRepository(contextProvidersTest, mealsService)
        viewModel = MealsViewModel(contextProvidersTest, repository)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `get meals change view state to LOADED if there is Albums`() {
        viewModel.getMeals()
        coVerifyOrder {
            stateObserver.onChanged(ViewState.Loading)
            stateObserver.onChanged(any())
        }
    }
}