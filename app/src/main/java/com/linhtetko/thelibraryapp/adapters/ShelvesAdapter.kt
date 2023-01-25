package com.linhtetko.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.delegates.ShelvesDelegate
import com.linhtetko.thelibraryapp.views.viewholders.ShelfViewHolder

class ShelvesAdapter(
    private var shelves: List<ShelfVO>,
    private val shelvesDelegate: ShelvesDelegate,
    private val owner: LifecycleOwner
): RecyclerView.Adapter<ShelfViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_shelf, parent, false)
        return ShelfViewHolder(view, shelvesDelegate, owner)
    }

    override fun onBindViewHolder(holder: ShelfViewHolder, position: Int) {
        holder.bindData(shelves[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<ShelfVO>){
        shelves = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = shelves.size
}