package com.linhtetko.thelibraryapp.views.viewholders

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.delegates.AddToShelfDelegate
import kotlinx.android.synthetic.main.view_holder_add_to_shelf.view.*
import kotlinx.android.synthetic.main.view_holder_shelf.view.*

class AddToShelfViewHolder(itemView: View, private val addToShelfDelegate: AddToShelfDelegate,private val owner: LifecycleOwner) :
    RecyclerView.ViewHolder(itemView) {
    fun bindData(shelfVO: ShelfVO) {
        itemView.tvAddToShelfTitle.text = shelfVO.title
        itemView.cbAddToShelf.isChecked = shelfVO.isChecked

        addToShelfDelegate.bindForShelfImageAndCount(shelfVO.id, itemView.ivAddToShelfImg, itemView.tvAddToShelfBookCount, owner)

        itemView.cbAddToShelf.setOnCheckedChangeListener { _, b ->
            addToShelfDelegate.onCheckedChange(shelfVO.title, b)
        }
    }
}