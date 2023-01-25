package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class GoogleBookVO(
    @SerializedName("items")
    val items: List<ItemVO>? = listOf(),
    @SerializedName("kind")
    val kind: String? = "",
    @SerializedName("totalItems")
    val totalItems: Int? = 0
)