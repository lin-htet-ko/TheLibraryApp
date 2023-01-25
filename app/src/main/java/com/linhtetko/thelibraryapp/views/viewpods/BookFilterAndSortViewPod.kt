package com.linhtetko.thelibraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.adapters.LibraryFilterChipAdapter
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import kotlinx.android.synthetic.main.viewpod_book_filter_and_sort.view.*

class BookFilterAndSortViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var mFilterChipAdapter: LibraryFilterChipAdapter? = null
    private var mBookAdapter: BookAdapter? = null

    fun setUpYourSortButtonText(text: String) {
        btnYourBookSort.text = text
    }

    fun setUpViewAsButtonListener(action: () -> Unit) {
        ivYourBookViewAs.setOnClickListener {
            action()
        }
    }

    fun setUpSortButtonListener(action: () -> Unit) {
        btnYourBookSort.setOnClickListener {
            action()
        }
    }

    fun setUpBookAdapter(adapter: BookAdapter) {
        mBookAdapter = adapter
        rvYourBooks.adapter = mBookAdapter
    }

    fun setUpNewBooks(books: List<BookVO>) {
        mBookAdapter?.setNewData(books)
    }

    fun getBooks(): List<BookVO>? {
        return mBookAdapter?.getBooks()
    }

    fun setBooksLayoutManager(manager: RecyclerView.LayoutManager){
        rvYourBooks.layoutManager = manager
    }

    fun setUpCategoryAdapter(adapter: LibraryFilterChipAdapter){
        mFilterChipAdapter = adapter
        rvBookCategory.setHasFixedSize(false)
        rvBookCategory.adapter = mFilterChipAdapter
    }

    fun setUpChips(chips: List<ChipVO>){
        mFilterChipAdapter?.setNewData(chips)
    }
}