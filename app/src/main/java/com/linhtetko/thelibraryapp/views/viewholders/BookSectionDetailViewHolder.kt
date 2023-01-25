package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDetailDelegate
import kotlinx.android.synthetic.main.view_holder_book_section_detail.view.*

class BookSectionDetailViewHolder(itemView: View, private val bookDelegate: BookDelegate) : RecyclerView.ViewHolder(itemView) {

    init {

    }

    fun bindData(bookVO: BookVO) {
        Glide.with(itemView.context).load(bookVO.bookImage).into(itemView.ivBookSectionDetail)
        itemView.tvBookSectionDetailName.text = bookVO.title ?: ""
        itemView.tvBookSectionDetailAuthor.text = bookVO.author ?: ""

        val json = Gson().toJson(bookVO)
        itemView.ivBookSectionDetailMore.setOnClickListener {
            bookDelegate.onClickBookMore(json)
        }
        itemView.setOnClickListener {
            bookDelegate.onClickBookItem(json)
        }
    }
}