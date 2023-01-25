package com.linhtetko.thelibraryapp.data.models

import com.linhtetko.thelibraryapp.data.vos.google.GoogleBookVO
import com.linhtetko.thelibraryapp.data.vos.google.ItemVO
import io.reactivex.rxjava3.core.Observable

interface GoogleBookModel {

    fun search(query: String): Observable<List<ItemVO>>
}