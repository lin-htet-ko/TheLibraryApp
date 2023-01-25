package com.linhtetko.thelibraryapp.mvp.views

import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.delegates.MainHomeCommunicationDelegate
import com.linhtetko.thelibraryapp.delegates.MainLibraryCommunicationDelegate

interface MainView: BaseView, MainHomeCommunicationDelegate, MainLibraryCommunicationDelegate {
    fun showBookOverviewBottomSheet(it: BookVO?)
    fun navigateToSearchScreen()
}