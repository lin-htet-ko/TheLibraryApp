package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {

    private var mainView: MainView? = null
    private val theLibraryModel: TheLibraryModel = TheLibraryModelImpl

    override fun getBook(title: String, author: String, owner: LifecycleOwner){
        theLibraryModel.getBook(title, author)?.observe(owner){
            mainView?.showBookOverviewBottomSheet(it)
        }
    }

    override fun deleteBookFromLibrary(title: String, author: String) {
        theLibraryModel.deleteBook(title, author)
    }

    override fun initView(view: MainView) {
        mainView = view
    }

    override fun onSearchbarClick() {
        mainView?.navigateToSearchScreen()
    }

    override fun onProfileClick() {

    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapBack() {
        mainView?.onBack()
    }


}