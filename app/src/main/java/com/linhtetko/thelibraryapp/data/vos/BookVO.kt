package com.linhtetko.thelibraryapp.data.vos


import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.data.vos.google.ItemVO
import com.linhtetko.thelibraryapp.persistance.typeconverters.BuyLinkTypeConverter

@Entity(
    tableName = "books", primaryKeys = ["author", "title"], foreignKeys = [
        ForeignKey(
            entity = ShelfVO::class,
            parentColumns = ["_id"],
            childColumns = ["self_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
@TypeConverters(
    BuyLinkTypeConverter::class
)
data class BookVO(

    @SerializedName("age_group")
    @ColumnInfo(name = "age_group")
    val ageGroup: String? = null,

    @ColumnInfo(name = "amazon_product_url")
    @SerializedName("amazon_product_url")
    val amazonProductUrl: String? = null,

    @ColumnInfo(name = "article_chapter_link")
    @SerializedName("article_chapter_link")
    val articleChapterLink: String? = null,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author: String,

    @ColumnInfo(name = "book_image")
    @SerializedName("book_image")
    val bookImage: String? = null,

    @ColumnInfo(name = "book_image_height")
    @SerializedName("book_image_height")
    val bookImageHeight: Int? = null,

    @ColumnInfo(name = "book_image_width")
    @SerializedName("book_image_width")
    val bookImageWidth: Int? = null,

    @ColumnInfo(name = "book_review_link")
    @SerializedName("book_review_link")
    val bookReviewLink: String? = null,

    @ColumnInfo(name = "book_uri")
    @SerializedName("book_uri")
    val bookUri: String? = null,

    @ColumnInfo(name = "buy_links")
    @SerializedName("buy_links")
    val buyLinks: List<BuyLinkVO?>? = null,

    @ColumnInfo(name = "contributor")
    @SerializedName("contributor")
    val contributor: String? = null,

    @ColumnInfo(name = "contributor_note")
    @SerializedName("contributor_note")
    val contributorNote: String? = null,

    @ColumnInfo(name = "created_date")
    @SerializedName("created_date")
    val createdDate: String? = null,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String? = null,

    @ColumnInfo(name = "first_chapter_link")
    @SerializedName("first_chapter_link")
    val firstChapterLink: String? = null,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: String? = null,

    @ColumnInfo(name = "primary_isbn10")
    @SerializedName("primary_isbn10")
    val primaryIsbn10: String? = null,

    @ColumnInfo(name = "primary_isbn13")
    @SerializedName("primary_isbn13")
    val primaryIsbn13: String? = null,

    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    val publisher: String? = null,

    @ColumnInfo(name = "rank")
    @SerializedName("rank")
    val rank: Int? = null,

    @ColumnInfo(name = "rank_last_week")
    @SerializedName("rank_last_week")
    val rankLastWeek: Int? = null,

    @ColumnInfo(name = "sunday_review_link")
    @SerializedName("sunday_review_link")
    val sundayReviewLink: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "updated_date")
    @SerializedName("updated_date")
    val updatedDate: String? = null,

    @ColumnInfo(name = "weeks_on_list")
    @SerializedName("weeks_on_list")
    val weeksOnList: Int? = null,

    @ColumnInfo(name = "list_name")
    val listName: String? = null,

    @ColumnInfo(name = "self_id")
    var selfId: Int? = 1
) {
    @Ignore
    var viewType: Int = BookAdapter.GRID_SMALL_VIEW

    companion object {
        fun toBookVo(googleBookVO: ItemVO): BookVO {
            return BookVO(
                author = if (googleBookVO.volumeInfo?.authors == null) "" else googleBookVO.volumeInfo.authors.filterNotNull()
                    .joinToString(separator = ",") { it },
                bookImage = googleBookVO.volumeInfo?.imageLinks?.thumbnail,
                publisher = googleBookVO.volumeInfo?.publisher,
                description = googleBookVO.volumeInfo?.description,
                title = googleBookVO.volumeInfo?.title ?: "",
                listName = googleBookVO.kind
            )
        }
    }
}