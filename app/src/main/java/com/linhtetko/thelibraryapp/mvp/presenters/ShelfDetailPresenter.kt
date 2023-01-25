package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.FilterChipDelegate
import com.linhtetko.thelibraryapp.delegates.ShelfDelegate
import com.linhtetko.thelibraryapp.delegates.YourBookDelegate
import com.linhtetko.thelibraryapp.mvp.views.ShelfDetailView
import com.linhtetko.thelibraryapp.mvp.views.YourBookView

interface ShelfDetailPresenter : BasePresenter<ShelfDetailView>, BookDelegate, FilterChipDelegate {
    fun getBookByShelf(id: Int, owner: LifecycleOwner)
    fun deleteBookFromLibrary(title: String, author: String)
    fun deleteShelf(id: Int)
    fun renameShelf(id: Int, title: String)
}