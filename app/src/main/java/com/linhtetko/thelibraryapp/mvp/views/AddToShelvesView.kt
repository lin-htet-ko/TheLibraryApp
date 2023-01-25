package com.linhtetko.thelibraryapp.mvp.views

import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.imageview.ShapeableImageView
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO

interface AddToShelvesView: BaseView {
    fun operateBookVO(vo: BookVO)
    fun showShelves(it: List<ShelfVO>?)
    fun navigateToBack()
    fun bindForShelfCount(count: Int?, tvShelfBookCount: AppCompatTextView)
    fun bindForShelfImage(string: String, ivShelf: ShapeableImageView)
}