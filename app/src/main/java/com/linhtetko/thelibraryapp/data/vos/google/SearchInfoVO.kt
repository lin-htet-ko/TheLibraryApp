package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class SearchInfoVO(
    @SerializedName("textSnippet")
    val textSnippet: String? = null
)