package com.linhtetko.thelibraryapp.views.viewholders

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.delegates.FilterChipDelegate
import kotlinx.android.synthetic.main.view_holder_library_filter_chip.view.*

class LibraryFilterChipViewHolder(itemView: View, private val filterChipDelegate: FilterChipDelegate) : RecyclerView.ViewHolder(itemView) {

    var mFilterChipVO: ChipVO? = null

    fun bindData(filterChipVO: ChipVO){
        mFilterChipVO = filterChipVO

        itemView.cpLibraryFilter.text = filterChipVO.name
        if (filterChipVO.isSelected){
            itemView.cpLibraryFilter.apply {
                setBackgroundResource(R.drawable.background_chip_selected)
                setTextColor(itemView.context.resources.getColor(R.color.white))
            }
        }else{
            itemView.cpLibraryFilter.apply {
                setBackgroundResource(R.drawable.background_chip_unselected)
                setTextColor(itemView.context.resources.getColor(android.R.color.darker_gray))
            }
        }
        itemView.setOnClickListener {
            filterChipDelegate.onSelect(filterChipVO.name)
        }
    }
}