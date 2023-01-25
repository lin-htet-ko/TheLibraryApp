package com.linhtetko.thelibraryapp.mvp.views

import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.imageview.ShapeableImageView
import com.linhtetko.thelibraryapp.data.vos.ShelfVO

interface YourShelvesView: BaseView {
    fun navigateToCreateShelf()
    fun hideEmptyView()
    fun showShelves(shelves: List<ShelfVO>?)
    fun showEmptyView()
    fun bindForShelfCount(count: Int?, tvShelfBookCount: AppCompatTextView)
    fun bindForShelfImage(bookImage: String?, ivShelf: ShapeableImageView)
    fun navigateToShelfDetail(id: Int)
}