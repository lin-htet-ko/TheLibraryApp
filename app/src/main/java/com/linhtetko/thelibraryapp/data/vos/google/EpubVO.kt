package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class EpubVO(
    @SerializedName("acsTokenLink")
    val acsTokenLink: String? = null,
    @SerializedName("isAvailable")
    val isAvailable: Boolean? = null
)