package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class SaleInfoVO(
    @SerializedName("buyLink")
    val buyLink: String? = "",
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("isEbook")
    val isEbook: Boolean? = false,
    @SerializedName("listPrice")
    val listPrice: ListPriceVO? = ListPriceVO(),
    @SerializedName("offers")
    val offers: List<OfferVO>? = listOf(),
    @SerializedName("retailPrice")
    val retailPrice: RetailPriceVOX? = RetailPriceVOX(),
    @SerializedName("saleability")
    val saleability: String? = ""
)