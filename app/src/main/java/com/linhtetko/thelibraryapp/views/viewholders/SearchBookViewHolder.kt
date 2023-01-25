package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.SearchBookDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*
import kotlinx.android.synthetic.main.view_holder_search_book.view.*

class SearchBookViewHolder(itemView: View, private val bookDelegate: SearchBookDelegate) : ViewHolder(itemView) {

    fun bindData(bookVO: BookVO) {
        itemView.apply {
            tvSearchBookName.text = bookVO.title ?: ""
            tvSearchBookAuthor.text = bookVO.author ?: ""
            Glide.with(itemView.context).load(bookVO.bookImage).into(ivSearchBook)

            val json = Gson().toJson(bookVO)
            itemView.setOnClickListener { bookDelegate.onSearchBookClick(json) }
        }
    }
}