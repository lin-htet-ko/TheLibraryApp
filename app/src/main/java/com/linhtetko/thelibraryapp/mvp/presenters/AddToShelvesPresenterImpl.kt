package com.linhtetko.thelibraryapp.mvp.presenters

import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.android.material.imageview.ShapeableImageView
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.mvp.views.AddToShelvesView

class AddToShelvesPresenterImpl : ViewModel(), AddToShelvesPresenter {

    private var mView: AddToShelvesView? = null
    private val theLibraryModel: TheLibraryModel = TheLibraryModelImpl
    private var bookVO: BookVO? = null
    private var shelves: List<ShelfVO> = listOf()

    override fun getBookOneTime(title: String, author: String) {
        val vo = theLibraryModel.getBookOneTime(title, author)
        if (vo != null) {
            bookVO = vo
            shelves = shelves.map {
                if (it.id == bookVO!!.selfId){
                    it.isChecked = true
                }
                it
            }
            mView?.showShelves(shelves)
            mView?.operateBookVO(vo)
        }
    }

    override fun onTapDone() {
        shelves.filter { it.isChecked }.mapNotNull { shelf ->
            val book = bookVO?.copy(selfId = shelf.id)
            if (book != null) {
                theLibraryModel.insertBook(book)
            }
            book
        }

        mView?.navigateToBack()
    }


    override fun initView(view: AddToShelvesView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        theLibraryModel.getShelves()?.observe(owner) {
            shelves  = it.map { shelf ->
                if (shelf.id == bookVO!!.selfId){
                    shelf.isChecked = true
                }
                shelf
            }
            mView?.showShelves(shelves)
        }
    }

    override fun onTapBack() {
        mView?.onBack()
    }

    override fun onCheckedChange(name: String, state: Boolean) {
        shelves = shelves.map {
            if (it.title == name) {
                it.isChecked = state
            }
            it
        }
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
}