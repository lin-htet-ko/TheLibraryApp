package com.linhtetko.thelibraryapp.data.models

import android.util.Log.d
import android.util.Log.e
import com.linhtetko.thelibraryapp.data.vos.google.GoogleBookVO
import com.linhtetko.thelibraryapp.data.vos.google.ItemVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GoogleBookModelImpl : GoogleBookModel, BaseModel() {

    override fun search(
        query: String
    ): Observable<List<ItemVO>> {

        return googleBooksApi
            .getGoogleBook(query = query)
            .map { it.items ?: listOf<ItemVO>() }
            .onErrorResumeNext { Observable.just(listOf()) }
    }

}