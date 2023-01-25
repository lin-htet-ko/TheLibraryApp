package com.linhtetko.thelibraryapp.network.responses


import com.google.gson.annotations.SerializedName
import com.linhtetko.thelibraryapp.data.vos.SectionDetailResultVO

data class SectionDetailResponse(
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("last_modified")
    val lastModified: String? = null,
    @SerializedName("num_results")
    val numResults: Int? = null,
    @SerializedName("results")
    val results: List<SectionDetailResultVO?>? = null,
    @SerializedName("status")
    val status: String? = null
)