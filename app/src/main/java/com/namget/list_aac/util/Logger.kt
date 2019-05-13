package com.namget.list_aac.util

import android.util.Log
import com.namget.list_aac.BuildConfig

class Logger {

    fun d(t: Throwable) {
        if (BuildConfig.DEBUG)
            Log.d("d", t.toString())
    }

    fun e(t: Throwable) {
        if (BuildConfig.DEBUG)
            Log.e("e", t.toString())
    }

}