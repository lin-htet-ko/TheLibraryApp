package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class ReadingModesVO(
    @SerializedName("image")
    val image: Boolean? = null,
    @SerializedName("text")
    val text: Boolean? = null
)