package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class ListPriceVO(
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("currencyCode")
    val currencyCode: String? = null
)