package com.linhtetko.thelibraryapp.mvp.views

import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.delegates.SectionDetailActivityAndFragmentCommunicationDelegate

interface SectionDetailView: BaseView, SectionDetailActivityAndFragmentCommunicationDelegate {
    fun onTapBookSectionDetailMore(bookVO: String)
    fun showSectionDetailData(sectionBookVO: SectionBookVO)
}