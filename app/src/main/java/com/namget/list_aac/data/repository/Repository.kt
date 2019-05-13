package com.namget.list_aac.data.repository

import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.data.model.PhotoList
import io.reactivex.Single

interface Repository {
    fun getPhotoList(
        clientId: String,
        page: Int = 10,
        perPage: Int = 10,
        orderBy: String = "lastest"
    ): Single<PhotoList>
    fun getPhotoDetail(id: String): Single<Photo>
}