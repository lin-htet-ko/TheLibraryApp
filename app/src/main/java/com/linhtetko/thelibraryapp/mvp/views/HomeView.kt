package com.linhtetko.thelibraryapp.mvp.views

import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO

interface HomeView: BaseView {
    fun showEbooks(ebooks: List<SectionBookVO>?)
    fun onTapBookMore(title: String, author: String)
    fun onTapSectionBookMore(sectionName: String)
    fun navigateToBookDetail(title: String, author: String)
    fun navigateToBookDetail(json: String)
    fun showRecentBooks(it: List<BookVO>?)
    fun onTapSectionBookMoreAction(json: String)
}