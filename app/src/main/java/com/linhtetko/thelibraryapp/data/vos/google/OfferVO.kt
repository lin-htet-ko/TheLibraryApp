package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class OfferVO(
    @SerializedName("finskyOfferType")
    val finskyOfferType: Int? = 0,
    @SerializedName("giftable")
    val giftable: Boolean? = false,
    @SerializedName("listPrice")
    val listPrice: ListPriceVOX? = ListPriceVOX(),
    @SerializedName("retailPrice")
    val retailPrice: RetailPriceVO? = RetailPriceVO()
)