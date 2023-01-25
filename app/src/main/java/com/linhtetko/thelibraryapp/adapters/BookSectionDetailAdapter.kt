package com.linhtetko.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDetailDelegate
import com.linhtetko.thelibraryapp.views.viewholders.BookSectionDetailViewHolder

class BookSectionDetailAdapter(
    private var books: List<BookVO> = listOf(),
    private val bookDelegate: BookDelegate
): RecyclerView.Adapter<BookSectionDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSectionDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book_section_detail, parent, false)
        return BookSectionDetailViewHolder(view, bookDelegate)
    }

    override fun onBindViewHolder(holder: BookSectionDetailViewHolder, position: Int) {
        if (books.isNotEmpty()){
            holder.bindData(books[position])
        }
    }

    override fun getItemCount(): Int = books.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<BookVO>) {
        books = items
        notifyDataSetChanged()
    }
}