package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class ImageLinksVO(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null
)