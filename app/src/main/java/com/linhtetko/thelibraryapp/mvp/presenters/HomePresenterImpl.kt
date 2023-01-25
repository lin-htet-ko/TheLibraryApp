package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.mvp.views.HomeView

class HomePresenterImpl : ViewModel(), HomePresenter {

    private var homeView: HomeView? = null

    private val mLibraryModel: TheLibraryModel = TheLibraryModelImpl

    override fun onClickOfflineMode(title: String, author: String) {

    }

    override fun onClickMore(title: String, author: String) {
        homeView?.onTapBookMore(title, author)
    }

    override fun onClickRecentViewedItem(title: String, author: String) {
        homeView?.navigateToBookDetail(title, author)
    }

    override fun onClickBookItem(json: String) {
        homeView?.navigateToBookDetail(json)
    }

    override fun onClickBookMore(json: String) {
        homeView?.onTapSectionBookMoreAction(json)
    }

    override fun onClickBookSectionSeeMore(sectionName: String) {
        homeView?.onTapSectionBookMore(sectionName)
    }

    override fun initView(view: HomeView) {
        homeView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        mLibraryModel.getBooks()?.observe(owner) {
            homeView?.showRecentBooks(it)
        }

        mLibraryModel.getBookSections {
            homeView?.showError(it)
        }?.observe(owner) {
            homeView?.showEbooks(it)
        }
    }

    override fun onTapBack() {
        homeView?.onBack()
    }
}