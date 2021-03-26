package com.weightwatchers.ww_exercise_01.ui.main.meal

import android.view.View
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.google.android.material.snackbar.Snackbar
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.base.BaseFragment
import com.weightwatchers.ww_exercise_01.base.ViewState
import com.weightwatchers.ww_exercise_01.data.remote.meals.Meal
import com.weightwatchers.ww_exercise_01.databinding.MealsFragmentBinding
import kotlinx.android.synthetic.main.meals_fragment.*

class MealsFragment(
        override val viewModel: MealsViewModel
        ) : BaseFragment<MealsFragmentBinding,MealsViewModel>() {

    private val adapter = MealsAdapter(::selectMeal)

    override fun getLayoutId(): Int = R.layout.meals_fragment

    override fun setup() {
        binding.vm = viewModel
        /*
        * save recycler view state while configuration change(change orientation)
        * https://medium.com/androiddevelopers/restore-recyclerview-scroll-position-a8fbdc9a9334
        * */
        adapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        mealsRecyclerView.adapter = adapter
        viewModel.getMeals()
        handleSwipeToRefresh()
    }

    private fun handleSwipeToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            viewModel.getMeals()
            swipeToRefresh.isRefreshing = false
        }
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

    private fun selectMeal(meal: Meal){
        Snackbar.make(mealsContainer, meal.getFormattedTags(), Snackbar.LENGTH_SHORT).show()
    }
}