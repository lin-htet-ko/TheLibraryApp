package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LiveData
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.mvp.views.CreateShelvesView

interface CreateShelvesPresenter: BasePresenter<CreateShelvesView> {

    fun insertNewShelf(shelfVO: ShelfVO)
    fun updateShelf(id: Int, name: String)
    fun getShelf(id: Int): ShelfVO?
}