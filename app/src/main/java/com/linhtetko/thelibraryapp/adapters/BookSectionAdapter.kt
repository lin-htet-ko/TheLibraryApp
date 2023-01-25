package com.linhtetko.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDelegate
import com.linhtetko.thelibraryapp.views.viewholders.BookSectionViewHolder

class BookSectionAdapter(
    private val bookDelegate: BookDelegate,
    private val bookSectionDelegate: BookSectionDelegate,
    private var sections: List<SectionBookVO> = listOf()
) : RecyclerView.Adapter<BookSectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_book_section, parent, false)
        return BookSectionViewHolder(view, bookDelegate, bookSectionDelegate)

    }

    override fun onBindViewHolder(holder: BookSectionViewHolder, position: Int) {
        holder.bindData(sections[position])
    }

    override fun getItemCount(): Int = sections.size

    @SuppressLint("NotifyDataSetChanged")
    fun setSections(items: List<SectionBookVO>){
        sections = items
        notifyDataSetChanged()
    }
}