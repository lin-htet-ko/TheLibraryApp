package com.linhtetko.thelibraryapp.data.models

import android.content.Context
import com.linhtetko.thelibraryapp.network.api.BooksApi
import com.linhtetko.thelibraryapp.network.api.GoogleBooksApi
import com.linhtetko.thelibraryapp.persistance.TheLibraryDatabase
import com.linhtetko.thelibraryapp.persistance.daos.BookDao
import com.linhtetko.thelibraryapp.persistance.daos.SectionBookDao
import com.linhtetko.thelibraryapp.persistance.daos.ShelvesDao
import com.linhtetko.thelibraryapp.utils.BOOK_API_BASE_URL
import com.linhtetko.thelibraryapp.utils.GOOGLE_API_BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected var theLibraryApi: BooksApi
    protected var googleBooksApi: GoogleBooksApi
    protected var theLibraryDatabase: TheLibraryDatabase? = null
    protected var sectionBookDao: SectionBookDao? = null
    protected var bookDao: BookDao? = null
    protected var shelvesDao: ShelvesDao? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BOOK_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val googleBooksApiRetrofit = Retrofit.Builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        theLibraryApi = retrofit.create(BooksApi::class.java)
        googleBooksApi = googleBooksApiRetrofit.create(GoogleBooksApi::class.java)


    }

    fun initDatabase(context: Context){
        theLibraryDatabase = TheLibraryDatabase.getInstance(context)
        sectionBookDao = theLibraryDatabase?.sectionBookDao()
        bookDao = theLibraryDatabase?.bookDao()
        shelvesDao = theLibraryDatabase?.shelvesDao()
    }
}