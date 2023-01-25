package com.linhtetko.thelibraryapp.mvp.presenters

import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDelegate
import com.linhtetko.thelibraryapp.delegates.RecentViewedDelegate
import com.linhtetko.thelibraryapp.delegates.SearchbarDelegate
import com.linhtetko.thelibraryapp.mvp.views.HomeView
import com.linhtetko.thelibraryapp.mvp.views.MainView

interface HomePresenter: BasePresenter<HomeView>, RecentViewedDelegate, BookDelegate, BookSectionDelegate {
}