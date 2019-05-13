package com.namget.list_aac.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.namget.list_aac.util.setImageWithGlide


//음... 에러가 계속난다..
@BindingAdapter("android:glideUrl")
fun ImageView.setGlideImage(url: String) {
    this.setImageWithGlide(url)
}