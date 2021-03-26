package com.weightwatchers.ww_exercise_01.utils.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.utils.extensions.addBaseUrl

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.image_loader)
            .error(R.drawable.ic_baseline_food)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()
    Glide.with(view)
            .load(imageUrl?.addBaseUrl())
            .apply(options)
            .into(view)
}