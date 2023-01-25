package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linhtetko.thelibraryapp.data.models.GoogleBookModel
import com.linhtetko.thelibraryapp.data.models.GoogleBookModelImpl
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.google.GoogleBookVO
import com.linhtetko.thelibraryapp.data.vos.google.ItemVO
import com.linhtetko.thelibraryapp.mvp.views.SearchView
import io.reactivex.rxjava3.core.Observable

class SearchPresenterImpl : ViewModel(), SearchPresenter {

    private var mView: SearchView? = null
    private val googleBookModel: GoogleBookModel = GoogleBookModelImpl
    override val books: MutableLiveData<List<BookVO>> by lazy { MutableLiveData(listOf()) }

    override fun search(query: String): Observable<List<ItemVO>> {
        return googleBookModel.search(query)
    }

    override fun initView(view: SearchView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onSearchBookClick(jsonBook: String) {
        mView?.navigateToBookDetail(jsonBook)
    }

    override fun onTapBack() {
        mView?.onBack()
    }
}