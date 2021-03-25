package com.weightwatchers.ww_exercise_01.ui.main.meal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.base.ViewState
import com.weightwatchers.ww_exercise_01.data.remote.meals.Meal
import com.weightwatchers.ww_exercise_01.data.repositories.MealsRepository
import com.weightwatchers.ww_exercise_01.utils.TestContextProvider
import com.weightwatchers.ww_exercise_01.utils.network.ApplicationException
import com.weightwatchers.ww_exercise_01.utils.network.ErrorType
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MealsViewModelTest{
    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()
    @MockK
    lateinit var repository: MealsRepository
    @MockK
    lateinit var stateObserver: Observer<ViewState>

    private lateinit var viewModel: MealsViewModel

    private val contextProvidersTest =
            TestContextProvider()

    private val dummyMeals = arrayListOf<Meal>(
            mockk(), mockk(), mockk(), mockk(), mockk()
    )

    private val dummyException = ApplicationException(
            type = ErrorType.Unexpected,
            errorMessageRes = R.string.error_unexpected
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = MealsViewModel(contextProvidersTest, repository)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `get meals change view state to LOADED if there is Albums`() {
        coEvery { repository.getMeals() } returns dummyMeals
        viewModel.getMeals()
        coVerifyOrder {
            stateObserver.onChanged(ViewState.Loading)
            stateObserver.onChanged(MealsViewState.Loaded(dummyMeals))
        }
    }

    @Test
    fun `get meals change view state to EMPTY if there is no Albums`() {
        coEvery { repository.getMeals() } returns arrayListOf()
        viewModel.getMeals()
        coVerifyOrder {
            stateObserver.onChanged(ViewState.Loading)
            stateObserver.onChanged(ViewState.Empty)
        }
    }

    @Test
    fun `get meals change view state to ERROR if repository throws an error`() {
        coEvery { repository.getMeals() } throws  dummyException
        viewModel.getMeals()
        coVerify {
            stateObserver.onChanged(ViewState.Error(dummyException.errorMessage, dummyException.errorMessageRes))
        }
    }

}