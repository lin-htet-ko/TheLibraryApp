package com.linhtetko.thelibraryapp.delegates

import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.imageview.ShapeableImageView

interface ShelvesDelegate {

    fun onShelfClick(id: Int)
    fun bindForShelfImageAndCount(
        id: Int,
        ivShelf: ShapeableImageView,
        tvShelfBookCount: AppCompatTextView,
        owner: LifecycleOwner
    )
}