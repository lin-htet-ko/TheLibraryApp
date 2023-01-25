package com.linhtetko.thelibraryapp.delegates

import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.imageview.ShapeableImageView

interface AddToShelfDelegate {
    fun onCheckedChange(name: String, state: Boolean)
    fun bindForShelfImageAndCount(
        id: Int,
        ivShelf: ShapeableImageView,
        tvShelfBookCount: AppCompatTextView,
        owner: LifecycleOwner
    )
}