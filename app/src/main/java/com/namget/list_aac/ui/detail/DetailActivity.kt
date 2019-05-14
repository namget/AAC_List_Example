package com.namget.list_aac.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.namget.list_aac.R
import com.namget.list_aac.databinding.ActivityDetailBinding
import com.namget.list_aac.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutId: Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    /**
     * 이코드의 문제점은 캐쉬에 없을경우 transition이 발동되질 않음
     */
    fun init() {
        val path = intent.getStringExtra(getString(R.string.imagePath))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(detailImage, getString(R.string.simple_activity_transition))
        }
        Glide.with(this).load(path).into(detailImage)
    }
}