package com.linhtetko.thelibraryapp.data.vos


import com.google.gson.annotations.SerializedName
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO

data class BookResultsVO(
    @SerializedName("bestsellers_date")
    val bestsellersDate: String? = null,
    @SerializedName("lists")
    val lists: List<SectionBookVO?>? = null,
    @SerializedName("next_published_date")
    val nextPublishedDate: String? = null,
    @SerializedName("previous_published_date")
    val previousPublishedDate: String? = null,
    @SerializedName("published_date")
    val publishedDate: String? = null,
    @SerializedName("published_date_description")
    val publishedDateDescription: String? = null
)