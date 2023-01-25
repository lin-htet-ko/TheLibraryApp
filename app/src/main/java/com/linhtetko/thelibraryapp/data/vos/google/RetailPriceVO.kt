package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class RetailPriceVO(
    @SerializedName("amountInMicros")
    val amountInMicros: Int? = null,
    @SerializedName("currencyCode")
    val currencyCode: String? = null
)