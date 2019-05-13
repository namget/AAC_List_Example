package com.namget.list_aac.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.namget.list_aac.util.setImageWithGlide

@BindingAdapter("android:url")
fun ImageView.MainBindingAdapter(url: String) {
    this.setImageWithGlide(url)
}