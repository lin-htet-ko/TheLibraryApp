package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.linhtetko.thelibraryapp.mvp.views.BaseView

interface BasePresenter<V: BaseView> {

    fun initView(view: V)
    fun onUiReady(owner: LifecycleOwner)

    fun onTapBack()

}