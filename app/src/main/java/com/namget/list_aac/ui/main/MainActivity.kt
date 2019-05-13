package com.namget.list_aac.ui.main

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namget.list_aac.R
import com.namget.list_aac.data.remote.NetworkManger
import com.namget.list_aac.data.repository.NetworkRepositoryImpl
import com.namget.list_aac.databinding.ActivityMainBinding
import com.namget.list_aac.ui.base.BaseActivity
import com.namget.list_aac.ui.main.adapter.MainAdapter
import com.namget.list_aac.util.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main
    lateinit var mainViewModel: MainViewModel
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initViewModel()

    }

    fun init() {

        myRecyclerView.apply {
            mainAdapter = MainAdapter(arrayListOf())
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = mainAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                }
            })
        }
    }

    fun initViewModel() {
        val mainViewModelFactory = MainViewModelFactory(NetworkRepositoryImpl(NetworkManger.getApiService()), Logger())
        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = mainViewModel

        //getList()
        mainViewModel.getPhotoList()
        mainViewModel.photoList.observe(this, Observer {
            if (mainAdapter.itemCount == 0) {
                mainAdapter.setItems(it)
            } else {
                mainAdapter.addItems(it)
            }
        })

        mainViewModel.isLoading.observe(this, Observer {
            if (it) {
//                preventTouch()
            } else {
//                clearTouch()
            }
        })


        mainViewModel.eventData.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                switchLayoutManager.setImageResource(R.drawable.ic_format_gird_black_24dp)
            }
        })


        binding.lifecycleOwner = this
    }



}
