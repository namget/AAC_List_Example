package com.namget.list_aac.data.repository

import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.util.Key
import com.namget.list_aac.util.Key.Companion.PER_PAGE
import io.reactivex.Single

interface Repository {
    fun getPhotoList(
        clientId: String,
        page: Int = 1,
        perPage: Int = PER_PAGE,
        orderBy: String = Key.ORDER_BY.POPULAR.type
    ): Single<ArrayList<Photo>>

    fun getPhotoDetail(id: String): Single<Photo>
}