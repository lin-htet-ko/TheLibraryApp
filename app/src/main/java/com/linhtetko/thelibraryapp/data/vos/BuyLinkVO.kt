package com.linhtetko.thelibraryapp.data.vos


import com.google.gson.annotations.SerializedName

data class BuyLinkVO(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)