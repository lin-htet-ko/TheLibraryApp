package com.linhtetko.thelibraryapp.data.vos


import com.google.gson.annotations.SerializedName

data class SectionDetailResultVO(
    @SerializedName("amazon_product_url")
    val amazonProductUrl: String? = null,
    @SerializedName("asterisk")
    val asterisk: Int? = null,
    @SerializedName("bestsellers_date")
    val bestsellersDate: String? = null,
    @SerializedName("book_details")
    val bookDetails: List<BookDetailVO?>? = null,
    @SerializedName("dagger")
    val dagger: Int? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("isbns")
    val isbns: List<IsbnVO?>? = null,
    @SerializedName("list_name")
    val listName: String? = null,
    @SerializedName("published_date")
    val publishedDate: String? = null,
    @SerializedName("rank")
    val rank: Int? = null,
    @SerializedName("rank_last_week")
    val rankLastWeek: Int? = null,
    @SerializedName("reviews")
    val reviews: List<ReviewVO?>? = null,
    @SerializedName("weeks_on_list")
    val weeksOnList: Int? = null
)