package com.namget.list_aac.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    abstract val layoutId: Int
    open lateinit var binding: B


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutId)
        super.onCreate(savedInstanceState)
    }
}