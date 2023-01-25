package com.linhtetko.thelibraryapp.data.vos

data class ChipVO(
    val name: String,
    var isSelected: Boolean = false,
    var type: Int = TEXT
){
    companion object{
        const val TEXT = 1
        const val CROSS = 0
    }
}
