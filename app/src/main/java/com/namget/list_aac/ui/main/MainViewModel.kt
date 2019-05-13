package com.namget.list_aac.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.data.repository.Repository
import com.namget.list_aac.ui.base.BaseViewModel
import com.namget.list_aac.util.Key
import com.namget.list_aac.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    val repository: Repository,
    val logger: Logger
) : BaseViewModel() {

    private val _isShowing = MutableLiveData<Boolean>()
    val isShowing: LiveData<Boolean> get() = _isShowing

    private val _photoList = MutableLiveData<ArrayList<Photo>>()
    val photoList: LiveData<ArrayList<Photo>> get() = _photoList


    fun getPhotoList() {
        addDisposable(
            repository.getPhotoList(Key.cliendKey).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        println(it)
                        _photoList.value = it.result
                    },
                    logger::e
                )
        )
    }


}