package com.weightwatchers.ww_exercise_01.ui.main.meal

import android.os.Bundle
import android.view.View
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.base.BaseFragment
import com.weightwatchers.ww_exercise_01.base.ViewState
import com.weightwatchers.ww_exercise_01.databinding.MealsFragmentBinding
import kotlinx.android.synthetic.main.meals_fragment.*

class MealsFragment(
        override val viewModel: MealsViewModel
        ) : BaseFragment<MealsFragmentBinding,MealsViewModel>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.meals_fragment

    override fun setup() {
        viewModel.getMeals()
    }

    override fun render(state: ViewState) {
        when(state){
            ViewState.Empty -> handleEmptyView()
            is MealsViewState.Loaded -> handleLoadedState()
        }
    }

    private fun handleLoadedState() {
        noMealsMsgTV.visibility = View.GONE
    }

    private fun handleEmptyView() {
        noMealsMsgTV.visibility = View.VISIBLE
    }

}