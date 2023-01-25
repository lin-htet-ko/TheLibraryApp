package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*

class BookViewHolder(itemView: View, private val bookDelegate: BookDelegate) : RecyclerView.ViewHolder(itemView) {

    fun bindData(bookVO: BookVO) {
        itemView.apply {
            tvBookName.text = bookVO.title ?: ""
            tvBookAuthor.text = bookVO.author ?: ""
            Glide.with(itemView.context).load(bookVO.bookImage).into(ivBook)

            val json = Gson().toJson(bookVO)
            itemView.setOnClickListener { bookDelegate.onClickBookItem(json) }
            itemView.ivBookMore.setOnClickListener { bookDelegate.onClickBookMore(json) }
        }
    }
}