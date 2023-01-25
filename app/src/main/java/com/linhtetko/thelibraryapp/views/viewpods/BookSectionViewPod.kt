package com.linhtetko.thelibraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDelegate
import kotlinx.android.synthetic.main.view_holder_book_section.view.*

class BookSectionViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var mBookSectionDelegate: BookSectionDelegate? = null
    private var mBookAdapter: BookAdapter? = null

    fun setUpBookSectionDelegate(sectionName: String?, bookSectionDelegate: BookSectionDelegate) {
        mBookSectionDelegate = bookSectionDelegate

        ivBookSeeMore.setOnClickListener {
            if (sectionName != null) {
                mBookSectionDelegate?.onClickBookSectionSeeMore(sectionName)
            }
        }
    }

    fun setUpContent(title: String) {
        tvBookSectionName.text = title
    }

    fun setUpBookAdapter(adapter: BookAdapter) {
        mBookAdapter = adapter
        rvBooks.adapter = mBookAdapter
    }


}