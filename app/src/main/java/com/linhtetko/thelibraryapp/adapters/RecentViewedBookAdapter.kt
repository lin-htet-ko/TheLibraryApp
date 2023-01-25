package com.linhtetko.thelibraryapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.RecentViewedDelegate
import kotlinx.android.synthetic.main.view_holder_recent_viewed_book.view.*

class RecentViewedBookAdapter(
    private val recentDelegate: RecentViewedDelegate,
    private var books: List<BookVO>
): CarouselAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewedBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recent_viewed_book, parent, false)
        return RecentViewedBookViewHolder(view, recentDelegate)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        if (books.isNotEmpty()){
            (holder as RecentViewedBookViewHolder).bindData(books[position])
        }
    }

    override fun getItemCount(): Int = books.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<BookVO>){
        books = items
        notifyDataSetChanged()
    }

    inner class RecentViewedBookViewHolder(itemView: View, private val recentViewedDelegate: RecentViewedDelegate) : CarouselAdapter.CarouselViewHolder(itemView) {

        fun bindData(bookVO: BookVO) {

            itemView.ivRecentViewed.contentDescription = bookVO.title
            Glide.with(itemView.context).load(bookVO.bookImage).into(itemView.ivRecentViewed)

            itemView.setOnClickListener {
                recentViewedDelegate.onClickRecentViewedItem(bookVO.title, bookVO.author)
            }
            itemView.ivMoreRecentViewed.setOnClickListener {
                recentViewedDelegate.onClickMore(bookVO.title, bookVO.author)
            }
            itemView.ivIsOfflineRecentViewed.setOnClickListener {
                recentViewedDelegate.onClickOfflineMode(bookVO.title, bookVO.author)
            }
        }
    }
}