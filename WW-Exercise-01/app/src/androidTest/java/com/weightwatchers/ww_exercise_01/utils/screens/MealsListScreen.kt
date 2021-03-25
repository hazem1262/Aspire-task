package com.weightwatchers.ww_exercise_01.utils.screens

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspresso.screens.KScreen
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.ui.main.meal.MealsFragment
import org.hamcrest.Matcher

object MealsListScreen : KScreen<MealsListScreen>(){
    override val layoutId: Int? = R.layout.meals_fragment
    override val viewClass: Class<*>? = MealsFragment::class.java

    val mealsRecyclerView = KRecyclerView({ withId(R.id.mealsRecyclerView) }, itemTypeBuilder = {
        itemType(::MealItem)
    })

    class MealItem(parent: Matcher<View>) : KRecyclerItem<MealItem>(parent) {
        val mealImage = KImageView { withId(R.id.mealImage) }
        val mealTitle = KTextView { withId(R.id.mealTitle) }
    }
}
