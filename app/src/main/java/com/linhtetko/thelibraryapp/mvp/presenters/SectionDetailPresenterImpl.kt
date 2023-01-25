package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.mvp.views.SectionDetailView

class SectionDetailPresenterImpl : ViewModel(), SectionDetailPresenter {

    private var sectionDetailView: SectionDetailView? = null
    private val mLibraryModel: TheLibraryModel = TheLibraryModelImpl

    override fun getSectionDetail(owner: LifecycleOwner, sectionName: String) {
        mLibraryModel.getSectionDetail(sectionName) {
            sectionDetailView?.showError(it)
        }?.observe(owner) {
            sectionDetailView?.showSectionDetailData(it)
        }
    }

    override fun initView(view: SectionDetailView) {
        sectionDetailView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onClickBookItem(json: String) {
        sectionDetailView?.onTapAboutBook(json)
    }

    override fun onClickBookMore(json: String) {
        sectionDetailView?.onTapBookSectionDetailMore(json)
    }

    override fun onTapBack() {
        sectionDetailView?.onBack()
    }
}