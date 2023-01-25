package com.linhtetko.thelibraryapp.mvp.views

interface BookDetailView: BaseView {
    fun showBookData(json: String?)
}