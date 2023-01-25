package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class ItemVO(
    @SerializedName("accessInfo")
    val accessInfo: AccessInfoVO? = AccessInfoVO(),
    @SerializedName("etag")
    val etag: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("kind")
    val kind: String? = "",
    @SerializedName("saleInfo")
    val saleInfo: SaleInfoVO? = SaleInfoVO(),
    @SerializedName("searchInfo")
    val searchInfo: SearchInfoVO? = SearchInfoVO(),
    @SerializedName("selfLink")
    val selfLink: String? = "",
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfoVO? = VolumeInfoVO()
)