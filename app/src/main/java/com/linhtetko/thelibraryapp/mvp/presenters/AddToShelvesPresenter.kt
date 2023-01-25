package com.linhtetko.thelibraryapp.mvp.presenters

import com.linhtetko.thelibraryapp.delegates.AddToShelfDelegate
import com.linhtetko.thelibraryapp.mvp.views.AddToShelvesView

interface AddToShelvesPresenter: BasePresenter<AddToShelvesView>, AddToShelfDelegate {
    fun getBookOneTime(title: String, author: String)
    fun onTapDone()
}