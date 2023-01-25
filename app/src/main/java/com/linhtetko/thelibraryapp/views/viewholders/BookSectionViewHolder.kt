package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDelegate
import com.linhtetko.thelibraryapp.views.viewpods.BookSectionViewPod
import kotlinx.android.synthetic.main.view_holder_book_section.view.*

class BookSectionViewHolder(
    private val itemView: View,
    private val bookDelegate: BookDelegate,
    private val bookSectionDelegate: BookSectionDelegate
) : RecyclerView.ViewHolder(itemView) {

    fun bindData(sectionBookVO: SectionBookVO) {
        (itemView as? BookSectionViewPod)?.apply {
            setUpContent(sectionBookVO.listName ?: "")
            val bookAdapter = BookAdapter(bookDelegate)
            setUpBookAdapter(bookAdapter)
            bookAdapter.setNewData(sectionBookVO.books?.filterNotNull() ?: listOf())
            setUpBookSectionDelegate(sectionBookVO.listName, bookSectionDelegate)
        }
    }

}