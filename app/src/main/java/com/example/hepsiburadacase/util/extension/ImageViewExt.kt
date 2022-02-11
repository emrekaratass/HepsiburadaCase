package com.example.hepsiburadacase.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hepsiburadacase.R

fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_baseline_image)
            .apply(RequestOptions().override(1280, 512).fitCenter())
            .into(this)
    }
}