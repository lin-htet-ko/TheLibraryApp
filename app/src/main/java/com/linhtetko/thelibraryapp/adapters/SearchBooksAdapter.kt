package com.linhtetko.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.SearchBookDelegate
import com.linhtetko.thelibraryapp.views.viewholders.SearchBookViewHolder

class SearchBooksAdapter(
    private var books: List<BookVO>,
    private val bookDelegate: SearchBookDelegate
): Adapter<SearchBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_search_book, parent, false)
        return SearchBookViewHolder(view, bookDelegate)
    }

    override fun onBindViewHolder(holder: SearchBookViewHolder, position: Int) {
        holder.bindData(books[position])
    }

    override fun getItemCount(): Int = books.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<BookVO>?) {
        if (items != null) {
            books = items
            notifyDataSetChanged()
        }
    }

    fun getBooks() = books
}