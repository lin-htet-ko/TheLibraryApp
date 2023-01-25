package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.mvp.views.LibraryView

class LibraryPresenterImpl : ViewModel(), LibraryPresenter {

    private var mView: LibraryView? = null


    override fun initView(view: LibraryView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapBack() {
        mView?.onBack()
    }


}