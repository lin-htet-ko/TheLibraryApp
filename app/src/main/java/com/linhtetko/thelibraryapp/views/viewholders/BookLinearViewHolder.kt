package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import kotlinx.android.synthetic.main.view_holder_book_linear.view.*

class BookLinearViewHolder(itemView: View, private val bookDelegate: BookDelegate) : RecyclerView.ViewHolder(itemView) {

    fun bindData(bookVO: BookVO) {
        itemView.apply {
            tvBookNameLinear.text = bookVO.title ?: ""
            tvBookAuthorLinear.text = bookVO.author ?: ""
            Glide.with(itemView.context).load(bookVO.bookImage).into(ivBookLinear)

            val json = Gson().toJson(bookVO)
            itemView.setOnClickListener { bookDelegate.onClickBookItem(json) }
            itemView.ivBookMoreLinear.setOnClickListener { bookDelegate.onClickBookMore(json) }
        }
    }
}