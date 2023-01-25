package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.mvp.views.BookDetailView

class BookDetailPresenterImpl: ViewModel(), BookDetailPresenter {

    private var bookDetailView: BookDetailView? = null
    private val theLibraryModel: TheLibraryModel = TheLibraryModelImpl

    override fun insertIntoRecentBooks(bookVO: BookVO) {
        theLibraryModel.insertBook(bookVO)
    }

    override fun getBook(title: String, author: String, owner: LifecycleOwner) {
        theLibraryModel.getBook(title, author)?.observe(owner){
            val json = Gson().toJson(it)
            bookDetailView?.showBookData(json)
        }
    }

    override fun onTapBack() {
        bookDetailView?.onBack()
    }

    override fun initView(view: BookDetailView) {
        bookDetailView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}