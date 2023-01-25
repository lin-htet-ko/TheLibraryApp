package com.linhtetko.thelibraryapp.mvp.views

import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.delegates.SortByDelegate
import com.linhtetko.thelibraryapp.delegates.ViewAsDelegate

interface YourBookView: BaseView, ViewAsDelegate, SortByDelegate {

    fun showBooks(books: List<BookVO>?)
    fun showSectionTitles(sections: List<ChipVO>?)
    fun onTapBookMore(bookVO: String)

    fun navigateToBookDetail(title: String, author: String)
}