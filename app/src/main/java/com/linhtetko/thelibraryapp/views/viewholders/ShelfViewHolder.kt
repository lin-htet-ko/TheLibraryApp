package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.delegates.ShelvesDelegate
import kotlinx.android.synthetic.main.view_holder_shelf.view.*

class ShelfViewHolder(itemView: View, private val shelvesDelegate: ShelvesDelegate, private val owner: LifecycleOwner) : RecyclerView.ViewHolder(itemView) {

    fun bindData(shelfVO: ShelfVO) {
        itemView.tvShelfName.text = shelfVO.title ?: ""
        shelvesDelegate.bindForShelfImageAndCount(shelfVO.id, itemView.ivShelf, itemView.tvShelfBookCount, owner)

        itemView.setOnClickListener {
            shelvesDelegate.onShelfClick(shelfVO.id)
        }

        itemView.ivShelfMore.setOnClickListener {
            shelvesDelegate.onShelfClick(shelfVO.id)
        }
    }
}