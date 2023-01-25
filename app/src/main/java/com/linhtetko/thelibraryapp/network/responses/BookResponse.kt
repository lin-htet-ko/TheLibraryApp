package com.linhtetko.thelibraryapp.network.responses


import com.google.gson.annotations.SerializedName
import com.linhtetko.thelibraryapp.data.vos.BookResultsVO

data class BookResponse(
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("num_results")
    val numResults: Int? = null,
    @SerializedName("results")
    val results: BookResultsVO? = null,
    @SerializedName("status")
    val status: String? = null
)