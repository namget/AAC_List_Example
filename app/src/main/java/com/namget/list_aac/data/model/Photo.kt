package com.namget.list_aac.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String,
    val description: String?,
    @SerializedName("alt_description")
    val altDescription: String,
    @SerializedName("urls")
    val urls: PhotoUrl

)