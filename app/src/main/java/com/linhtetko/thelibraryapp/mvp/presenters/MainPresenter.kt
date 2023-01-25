package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.RecentViewedDelegate
import com.linhtetko.thelibraryapp.delegates.SearchbarDelegate
import com.linhtetko.thelibraryapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>, SearchbarDelegate {
    fun getBook(title: String, author: String, owner: LifecycleOwner)
    fun deleteBookFromLibrary(title: String, author: String)

}