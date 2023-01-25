package com.linhtetko.thelibraryapp.mvp.presenters

import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.FilterChipDelegate
import com.linhtetko.thelibraryapp.delegates.YourBookDelegate
import com.linhtetko.thelibraryapp.mvp.views.YourBookView

interface YourBookPresenter : BasePresenter<YourBookView>, BookDelegate, FilterChipDelegate,
    YourBookDelegate {
}