package com.namget.list_aac.ui.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.namget.list_aac.R
import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.data.remote.NetworkManger
import com.namget.list_aac.data.repository.NetworkRepositoryImpl
import com.namget.list_aac.databinding.ActivityMainBinding
import com.namget.list_aac.ui.base.BaseActivity
import com.namget.list_aac.ui.base.RecyclerItemListener
import com.namget.list_aac.ui.detail.DetailActivity
import com.namget.list_aac.ui.main.adapter.MainAdapter
import com.namget.list_aac.util.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(), RecyclerItemListener {

    override val layoutId: Int = R.layout.activity_main
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
    private lateinit var mLayoutManager: StaggeredGridLayoutManager
    private var currentLayoutManagerType: LayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER
    private var list: ArrayList<Photo> = arrayListOf()

    enum class LayoutManagerType {
        GRID_LAYOUT_MANAGER, LINER_LAYOUT_MANAGER
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel()
    }

    fun init() {
        mainAdapter = MainAdapter(arrayListOf()).also {
            it.setItemClickListener(this)
        }
        mLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        myRecyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mainAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    //최상단
                    if (!recyclerView.canScrollVertically(-1)) {
                    }
                    //최하단
                    else if (!recyclerView.canScrollVertically(1)) {
                        if (recyclerView.adapter?.itemCount != 0)
                            mainViewModel.loadMore()
                    } else {
                    }
                }
            })
        }
    }

    fun setRecyclerViewLayoutManager() {
        when (currentLayoutManagerType) {
            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                switchLayoutManager.setImageResource(R.drawable.ic_format_gird_black_24dp)
                currentLayoutManagerType = LayoutManagerType.LINER_LAYOUT_MANAGER
                mLayoutManager.spanCount = 1
            }
            LayoutManagerType.LINER_LAYOUT_MANAGER -> {
                switchLayoutManager.setImageResource(R.drawable.ic_format_linear_black_24dp)
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER
                mLayoutManager.spanCount = 3
            }
        }
    }


    fun initViewModel() {
        val mainViewModelFactory = MainViewModelFactory(NetworkRepositoryImpl(NetworkManger.getApiService()), Logger())
        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)
        binding.mViewModel = mainViewModel

        //getList()
        mainViewModel.getPhotoList()

        mainViewModel.photoList.observe(this, Observer {
            list = it
            Log.e("test", "" + list.size)
            if (mainAdapter.itemCount == 0) {
                mainAdapter.setItems(list)
            } else {
                mainAdapter.addItems(list)
            }
        })

        mainViewModel.isLoading.observe(this, Observer {
            setTouchable(it)
        })

        mainViewModel.eventData.observe(this, Observer {
            it?.getContentIfNotHandled().let {
                if (it == MainViewModel.EventList.SWITCH_LAYOUT_MANAGER) {
                    if (mainViewModel.isLoading.value == false) {
                        setRecyclerViewLayoutManager()
                    }
                }
            }
        })

        binding.lifecycleOwner = this
    }

    override fun itemClick(view: View, path: String) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtra(getString(R.string.imagePath), path)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this@MainActivity,
                view,
                getString(R.string.simple_activity_transition)
            )
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    fun setTouchable(isLoading: Boolean) {


        if (isLoading) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

}
