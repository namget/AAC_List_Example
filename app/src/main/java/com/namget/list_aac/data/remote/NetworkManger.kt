package com.namget.list_aac.data.remote

import com.google.gson.GsonBuilder
import com.namget.list_aac.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManger {
    lateinit var api: ApiService

    fun getApiService(): ApiService {
        if (!::api.isInitialized) {
            val gson = GsonBuilder().setLenient().create()

            val interceptor = HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                } else {
                    setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            } as Interceptor

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            api = Retrofit.Builder().apply {
                addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                addConverterFactory(GsonConverterFactory.create(gson))
                client(client)
                baseUrl("https://api.unsplash.com/")
            }.build().create(ApiService::class.java)
        }
        return api
    }

}