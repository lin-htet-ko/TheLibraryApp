package com.linhtetko.thelibraryapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.thelibraryapp.data.vos.BookVO

class BooksTypeConverter {

    @TypeConverter
    fun toString(books: List<BookVO?>?): String?{
        return Gson().toJson(books)
    }

    @TypeConverter
    fun toBooks(json: String): List<BookVO?>?{
        return Gson().fromJson(json, object : TypeToken<List<BookVO?>?>(){}.type)
    }

}