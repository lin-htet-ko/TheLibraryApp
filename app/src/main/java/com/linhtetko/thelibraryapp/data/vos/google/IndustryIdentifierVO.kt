package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class IndustryIdentifierVO(
    @SerializedName("identifier")
    val identifier: String? = null,
    @SerializedName("type")
    val type: String? = null
)