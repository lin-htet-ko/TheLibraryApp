package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.mvp.views.BookDetailView

interface BookDetailPresenter: BasePresenter<BookDetailView> {

    fun insertIntoRecentBooks(bookVO: BookVO)
    fun getBook(title: String, author: String, owner: LifecycleOwner)
}