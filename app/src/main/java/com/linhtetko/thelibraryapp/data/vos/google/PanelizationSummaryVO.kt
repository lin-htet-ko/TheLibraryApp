package com.linhtetko.thelibraryapp.data.vos.google


import com.google.gson.annotations.SerializedName

data class PanelizationSummaryVO(
    @SerializedName("containsEpubBubbles")
    val containsEpubBubbles: Boolean? = null,
    @SerializedName("containsImageBubbles")
    val containsImageBubbles: Boolean? = null
)