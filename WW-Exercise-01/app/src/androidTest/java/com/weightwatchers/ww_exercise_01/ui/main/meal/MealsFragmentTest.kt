package com.weightwatchers.ww_exercise_01.ui.main.meal

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.weightwatchers.ww_exercise_01.ui.main.MainActivity
import com.weightwatchers.ww_exercise_01.utils.screens.MealsListScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MealsFragmentTest : TestCase(kaspressoBuilder = Kaspresso.Builder.simple().apply {
    beforeEachTest {
        ActivityScenario.launch(MainActivity::class.java)
    }
}) {
    private val mealItemTitle = "Summer Grilling"

    @Test
    fun when_main_activity_opens_list_of_albums_shown() = run {
        MealsListScreen{
            step("click on specific item"){
                mealsRecyclerView{
                    childWith<MealsListScreen.MealItem> {
                        withDescendant { withText(mealItemTitle) }
                    } perform{click()}
                }
            }
        }
    }
}