package com.namget.list_aac.ui.main

import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.data.repository.Repository
import com.namget.list_aac.ui.base.BaseViewModel
import com.namget.list_aac.util.Event
import com.namget.list_aac.util.Key
import com.namget.list_aac.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    val repository: Repository,
    val logger: Logger
) : BaseViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    private val _eventData = MutableLiveData<Event<EventList>>()
    val eventData: LiveData<Event<EventList>> get() = _eventData

    private val _photoList = MutableLiveData<ArrayList<Photo>>()
    val photoList: LiveData<ArrayList<Photo>> get() = _photoList


    enum class EventList {
        SWITCH_LAYOUT
    }

    fun switchLayoutManager(view: View) {
        println("1111111111111")
        Log.e("test", "ttttttttttttt")
        _eventData.value = Event(EventList.SWITCH_LAYOUT)
    }

    fun getPhotoList() {
        _isLoading.value = true
        addDisposable(
            repository.getPhotoList(Key.cliendKey).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _photoList.value = it
                    },
                    logger::e
                )
        )
    }


}