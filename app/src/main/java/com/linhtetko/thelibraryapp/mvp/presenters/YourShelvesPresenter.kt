package com.linhtetko.thelibraryapp.mvp.presenters

import com.linhtetko.thelibraryapp.delegates.ShelvesDelegate
import com.linhtetko.thelibraryapp.mvp.views.YourShelvesView

interface YourShelvesPresenter : BasePresenter<YourShelvesView>, ShelvesDelegate {
    fun onTapCreateShelf()
}