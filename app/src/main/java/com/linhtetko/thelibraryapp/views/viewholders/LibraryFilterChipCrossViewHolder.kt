package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.delegates.FilterChipDelegate
import kotlinx.android.synthetic.main.view_holder_filter_chip_cross.view.*

class LibraryFilterChipCrossViewHolder(itemView: View, private val filterChipDelegate: FilterChipDelegate) : RecyclerView.ViewHolder(itemView) {

    init {

        itemView.btnFilterChipClose.setOnClickListener {
            filterChipDelegate.onClose()
        }
    }
}