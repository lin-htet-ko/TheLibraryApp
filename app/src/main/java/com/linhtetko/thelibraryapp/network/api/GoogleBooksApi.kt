package com.linhtetko.thelibraryapp.network.api

import com.linhtetko.thelibraryapp.data.vos.google.GoogleBookVO
import com.linhtetko.thelibraryapp.utils.GOOGLE_API_GET_BOOKS
import com.linhtetko.thelibraryapp.utils.GOOGLE_API_KEY
import com.linhtetko.thelibraryapp.utils.GOOGLE_QUERY_API_KEY
import com.linhtetko.thelibraryapp.utils.GOOGLE_QUERY_API_SEARCH
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET(GOOGLE_API_GET_BOOKS)
    fun getGoogleBook(
        @Query("key") apiKey: String = GOOGLE_API_KEY,
        @Query("q") query: String
    ): Observable<GoogleBookVO>

}