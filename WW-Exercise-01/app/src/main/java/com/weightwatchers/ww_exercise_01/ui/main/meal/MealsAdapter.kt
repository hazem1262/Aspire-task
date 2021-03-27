package com.weightwatchers.ww_exercise_01.ui.main.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.data.remote.meals.Meal
import com.weightwatchers.ww_exercise_01.databinding.MealItemBinding

class MealsAdapter(
        val selectMeal:(Meal)->Unit
) : ListAdapter<Meal, RecyclerView.ViewHolder>(diffCallBack){
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(
                    oldItem: Meal,
                    newItem: Meal
            ): Boolean = newItem == oldItem

            override fun areContentsTheSame(
                    oldItem: Meal,
                    newItem: Meal
            ): Boolean = oldItem.title == newItem.title

        }

        @JvmStatic
        @BindingAdapter("meals")
        fun RecyclerView.bindItems(items: LiveData<List<Meal>>?) {
            val adapter = adapter as MealsAdapter
            items?.observeForever {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MealViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.meal_item, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MealViewHolder).bind(getItem(position))
    }

    inner class MealViewHolder(private var binding: MealItemBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(item:Meal){
            binding.apply {
                root.setOnClickListener {
                    selectMeal(item)
                }
                meal = item
                executePendingBindings()
            }
        }
    }
}