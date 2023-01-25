package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class RetailPriceVOX(
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("currencyCode")
    val currencyCode: String? = null
)