package com.namget.list_aac.data.repository

import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.data.remote.ApiService
import io.reactivex.Single

class NetworkRepositoryImpl(val apiService: ApiService) : Repository {

    override fun getPhotoList(clientId: String, page: Int, perPage: Int, orderBy: String): Single<ArrayList<Photo>> {
        return apiService.getPhotoList(clientId, page, perPage, orderBy)
    }

    override fun getPhotoDetail(id: String): Single<Photo> {
        return apiService.getPhotoDetail(id)
    }
}