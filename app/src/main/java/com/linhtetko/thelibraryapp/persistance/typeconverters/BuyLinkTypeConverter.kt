package com.linhtetko.thelibraryapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.thelibraryapp.data.vos.BuyLinkVO

class BuyLinkTypeConverter {

    @TypeConverter
    fun toString(books: List<BuyLinkVO?>?): String?{
        return Gson().toJson(books)
    }

    @TypeConverter
    fun toBuyLinks(json: String): List<BuyLinkVO?>?{
        return Gson().fromJson(json, object : TypeToken<List<BuyLinkVO?>?>(){}.type)
    }
}