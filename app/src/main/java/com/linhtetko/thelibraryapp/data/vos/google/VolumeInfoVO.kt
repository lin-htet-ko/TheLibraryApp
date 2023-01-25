package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class VolumeInfoVO(
    @SerializedName("allowAnonLogging")
    val allowAnonLogging: Boolean? = null,
    @SerializedName("authors")
    val authors: List<String?>? = null,
    @SerializedName("averageRating")
    val averageRating: Int? = null,
    @SerializedName("canonicalVolumeLink")
    val canonicalVolumeLink: String? = null,
    @SerializedName("categories")
    val categories: List<String?>? = null,
    @SerializedName("contentVersion")
    val contentVersion: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinksVO? = null,
    @SerializedName("industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifierVO?>? = null,
    @SerializedName("infoLink")
    val infoLink: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("maturityRating")
    val maturityRating: String? = null,
    @SerializedName("pageCount")
    val pageCount: Int? = null,
    @SerializedName("panelizationSummary")
    val panelizationSummary: PanelizationSummaryVO? = null,
    @SerializedName("previewLink")
    val previewLink: String? = null,
    @SerializedName("printType")
    val printType: String? = null,
    @SerializedName("publishedDate")
    val publishedDate: String? = null,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("ratingsCount")
    val ratingsCount: Int? = null,
    @SerializedName("readingModes")
    val readingModes: ReadingModesVO? = null,
    @SerializedName("subtitle")
    val subtitle: String? = null,
    @SerializedName("title")
    val title: String? = null
)