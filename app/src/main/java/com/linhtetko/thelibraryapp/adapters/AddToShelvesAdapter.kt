package com.linhtetko.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.delegates.AddToShelfDelegate
import com.linhtetko.thelibraryapp.views.viewholders.AddToShelfViewHolder

class AddToShelvesAdapter(
    private var shelves: List<ShelfVO> = listOf(),
    private val addToShelfDelegate: AddToShelfDelegate,
    private val owner: LifecycleOwner
): RecyclerView.Adapter<AddToShelfViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_add_to_shelf, parent, false)
        return AddToShelfViewHolder(view, addToShelfDelegate, owner)
    }

    override fun onBindViewHolder(holder: AddToShelfViewHolder, position: Int) {
        holder.bindData(shelves[position])
    }

    override fun getItemCount(): Int {
        return shelves.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpNewData(items: List<ShelfVO>){
        shelves = items
        notifyDataSetChanged()
    }
}