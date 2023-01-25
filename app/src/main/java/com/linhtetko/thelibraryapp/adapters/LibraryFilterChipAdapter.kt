package com.linhtetko.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.delegates.FilterChipDelegate
import com.linhtetko.thelibraryapp.views.viewholders.LibraryFilterChipCrossViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.LibraryFilterChipViewHolder

class LibraryFilterChipAdapter(
    private val filterChipDelegate: FilterChipDelegate,
    private var chips: List<ChipVO> = listOf()
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ChipVO.CROSS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_filter_chip_cross, parent, false)
                view.layoutParams =
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                LibraryFilterChipCrossViewHolder(view, filterChipDelegate)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_library_filter_chip, parent, false)
                LibraryFilterChipViewHolder(view, filterChipDelegate)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is LibraryFilterChipCrossViewHolder -> {}
            is LibraryFilterChipViewHolder -> {
                holder.bindData(chips[position])
            }
        }

    }

    override fun getItemCount(): Int = chips.size

    fun getChips() = chips

    override fun getItemViewType(position: Int): Int {
        return chips[position].type
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<ChipVO>) {
        chips = items
        notifyDataSetChanged()
    }
}