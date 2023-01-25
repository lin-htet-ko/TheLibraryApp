package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.mvp.views.CreateShelvesView

class CreateShelvesPresenterImpl : ViewModel(), CreateShelvesPresenter {

    private var mView: CreateShelvesView? = null
    private val theLibraryModel: TheLibraryModel = TheLibraryModelImpl

    override fun initView(view: CreateShelvesView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun insertNewShelf(shelfVO: ShelfVO) {
        theLibraryModel.insertShelf(shelfVO)
    }

    override fun updateShelf(id: Int, name: String) {
        theLibraryModel.renameShelf(id, name)
    }

    override fun getShelf(id: Int): ShelfVO? {
        return theLibraryModel.getShelfOneTime(id)
    }

    override fun onTapBack() {
        mView?.onBack()
    }
}