package com.linhtetko.thelibraryapp.network.api

import com.linhtetko.thelibraryapp.network.responses.BookResponse
import com.linhtetko.thelibraryapp.network.responses.SectionDetailResponse
import com.linhtetko.thelibraryapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BooksApi {

    @GET(GET_OVERVIEW_OF_BOOK)
    @Headers(
        "Accept: application/json"
    )
    fun getOverviewOfBooks(@Query(QUERY_API_KEY) apiKey: String = BOOK_API_KEY): Observable<BookResponse>

    @GET(GET_SECTION_DETAIL)
    @Headers(
        "Accept: application/json"
    )
    fun getSectionDetail(@Query(QUERY_LIST) sectionName: String): Observable<SectionDetailResponse>
}