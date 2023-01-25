package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDetailDelegate
import com.linhtetko.thelibraryapp.mvp.views.SectionDetailView

interface SectionDetailPresenter: BasePresenter<SectionDetailView>, BookDelegate {
    fun getSectionDetail(owner: LifecycleOwner, sectionName: String)
}