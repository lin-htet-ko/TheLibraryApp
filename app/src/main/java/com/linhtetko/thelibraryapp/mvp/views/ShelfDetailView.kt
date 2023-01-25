package com.linhtetko.thelibraryapp.mvp.views

import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.delegates.*

interface ShelfDetailView: BaseView, ViewAsDelegate, SortByDelegate, MainHomeCommunicationDelegate, ShelfDelegate {

    fun showBooks(books: List<BookVO>?)
    fun showSectionTitles(sections: List<ChipVO>?)
    fun onTapBookMore(bookVO: String)

    override fun navigateToBookDetail(title: String, author: String)
    fun bindForBookCount(count: Int?)
    fun bindForShelfName(title: String)
}