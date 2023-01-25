package com.linhtetko.thelibraryapp.data.vos


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.linhtetko.thelibraryapp.persistance.typeconverters.BooksTypeConverter

@Entity(tableName = "book_sections")
@TypeConverters(BooksTypeConverter::class)
data class SectionBookVO(
    @ColumnInfo(name = "books")
    @SerializedName("books")
    var books: List<BookVO?>? = null,

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    val displayName: String? = null,

    @ColumnInfo(name = "list_id")
    @PrimaryKey
    @SerializedName("list_id")
    val listId: Int? = null,

    @ColumnInfo(name = "list_image")
    @SerializedName("list_image")
    val listImage: String? = null,

    @ColumnInfo(name = "list_image_height")
    @SerializedName("list_image_height")
    val listImageHeight: Long? = null,

    @ColumnInfo(name = "list_image_width")
    @SerializedName("list_image_width")
    val listImageWidth: Long? = null,

    @ColumnInfo(name = "list_name")
    @SerializedName("list_name")
    val listName: String? = null,

    @ColumnInfo(name = "list_name_encoded")
    @SerializedName("list_name_encoded")
    val listNameEncoded: String? = null,

    @ColumnInfo(name = "updated")
    @SerializedName("updated")
    val updated: String? = null
)