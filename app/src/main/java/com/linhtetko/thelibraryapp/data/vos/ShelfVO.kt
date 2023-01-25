package com.linhtetko.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shelf")
data class ShelfVO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String
){
    @Ignore
    var isChecked = false
}
