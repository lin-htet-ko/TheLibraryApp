package com.linhtetko.thelibraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.viewpod_book_overview.view.*

class BookOverviewViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {


    fun setUpContent(title: String, author: String, imageUrl: String, type: String){

        Glide.with(context).load(imageUrl).into(ivBookOverview)
        tvBookOverviewTitle.text = title
        tvBookOverviewAuthor.text = author
        tvBookOverviewType.text = type
    }
}