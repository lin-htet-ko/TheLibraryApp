package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.android.material.imageview.ShapeableImageView
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.mvp.views.YourShelvesView

class YourShelvesPresenterImpl : ViewModel(), YourShelvesPresenter {

    private var mView: YourShelvesView? = null
    private val theLibraryModel: TheLibraryModel = TheLibraryModelImpl

    override fun onTapCreateShelf() {
        mView?.navigateToCreateShelf()
    }

    override fun initView(view: YourShelvesView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        theLibraryModel.getShelves()?.observe(owner) {
            mView?.showShelves(it)
            if (it.isEmpty()) {
                mView?.showEmptyView()
            } else {
                mView?.hideEmptyView()
            }
        }
    }

    override fun onShelfClick(id: Int) {
        mView?.navigateToShelfDetail(id)
    }

    override fun bindForShelfImageAndCount(
        id: Int,
        ivShelf: ShapeableImageView,
        tvShelfBookCount: AppCompatTextView,
        owner: LifecycleOwner
    ) {

        theLibraryModel.countBookByShelf(id)?.observe(owner) {
            mView?.bindForShelfCount(it, tvShelfBookCount)
        }
        theLibraryModel.getLastBookByShelf(id)?.observe(owner) {
            if (it != null)
                mView?.bindForShelfImage(it.bookImage ?: "", ivShelf)
        }
    }

    override fun onTapBack() {
        mView?.onBack()
    }


}