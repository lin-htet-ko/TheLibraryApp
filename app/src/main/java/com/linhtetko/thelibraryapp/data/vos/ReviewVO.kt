package com.linhtetko.thelibraryapp.data.vos


import com.google.gson.annotations.SerializedName

data class ReviewVO(
    @SerializedName("article_chapter_link")
    val articleChapterLink: String? = null,
    @SerializedName("book_review_link")
    val bookReviewLink: String? = null,
    @SerializedName("first_chapter_link")
    val firstChapterLink: String? = null,
    @SerializedName("sunday_review_link")
    val sundayReviewLink: String? = null
)