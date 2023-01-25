package com.linhtetko.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.views.viewholders.BookLinearViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.BookSectionDetailViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.BookViewHolder

class BookAdapter(
    private val bookDelegate: BookDelegate,
    private var books: List<BookVO> = listOf()
) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val LIST_VIEW = 1
        const val GRID_SMALL_VIEW = 2
        const val GRID_LARGE_VIEW = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            LIST_VIEW -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_book_linear, parent, false)
                return BookLinearViewHolder(view, bookDelegate)
            }
            GRID_LARGE_VIEW -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_book_section_detail, parent, false)
                return BookSectionDetailViewHolder(view, bookDelegate)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_book, parent, false)
                return BookViewHolder(view, bookDelegate)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is BookLinearViewHolder -> {
                holder.bindData(books[position])
            }
            is BookViewHolder -> {
                holder.bindData(books[position])
            }
            is BookSectionDetailViewHolder -> {
                holder.bindData(books[position])
            }
        }

    }

    override fun getItemCount(): Int = books.size

    override fun getItemViewType(position: Int): Int {
        return books[position].viewType
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<BookVO>) {
        books = items
        notifyDataSetChanged()
    }

    fun getBooks() = books
}