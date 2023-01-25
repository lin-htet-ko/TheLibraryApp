package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.MutableLiveData
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.google.GoogleBookVO
import com.linhtetko.thelibraryapp.data.vos.google.ItemVO
import com.linhtetko.thelibraryapp.delegates.SearchBookDelegate
import com.linhtetko.thelibraryapp.mvp.views.SearchView
import io.reactivex.rxjava3.core.Observable

interface SearchPresenter : BasePresenter<SearchView>, SearchBookDelegate{
    val books: MutableLiveData<List<BookVO>>
    abstract fun search(query: String): Observable<List<ItemVO>>
}