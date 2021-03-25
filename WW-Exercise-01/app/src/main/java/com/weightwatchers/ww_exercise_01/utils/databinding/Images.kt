package com.weightwatchers.ww_exercise_01.utils.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.weightwatchers.ww_exercise_01.utils.extensions.addBaseUrl


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view).load(imageUrl?.addBaseUrl()).into(view);
}