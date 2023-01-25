package com.linhtetko.thelibraryapp.data.vos


import com.google.gson.annotations.SerializedName

data class BookDetailVO(
    @SerializedName("age_group")
    val ageGroup: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("contributor")
    val contributor: String? = null,
    @SerializedName("contributor_note")
    val contributorNote: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("primary_isbn10")
    val primaryIsbn10: Int? = null,
    @SerializedName("primary_isbn13")
    val primaryIsbn13: String? = null,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("title")
    val title: String? = null
)