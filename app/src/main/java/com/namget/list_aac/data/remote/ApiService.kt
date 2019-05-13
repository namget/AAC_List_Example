package com.namget.list_aac.data.remote

import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.data.model.PhotoList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/photo")
    fun getPhotoList(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String
    ) : Single<PhotoList>

    @GET("/photo/{id}")
    fun getPhotoDetail(@Path("id") id: String) : Single<Photo>

}