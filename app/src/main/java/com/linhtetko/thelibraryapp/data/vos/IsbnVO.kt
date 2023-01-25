package com.linhtetko.thelibraryapp.data.vos


import com.google.gson.annotations.SerializedName

data class IsbnVO(
    @SerializedName("isbn10")
    val isbn10: String? = null,
    @SerializedName("isbn13")
    val isbn13: String? = null
)