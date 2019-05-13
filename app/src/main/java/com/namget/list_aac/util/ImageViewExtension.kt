package com.namget.list_aac.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.namget.list_aac.R

fun ImageView.setImageWithGlide(url: String) =
    url.let {
        try {
            Glide.with(context)
                .load(it)
                .apply(
                    RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.mipmap.ic_launcher_round)
                )
                .into(this)
        } catch (e: Exception) {
        }
    }