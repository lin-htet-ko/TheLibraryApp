package com.linhtetko.thelibraryapp.mvp.views

interface SearchView: BaseView {
    fun navigateToBookDetail(jsonBook: String)
}