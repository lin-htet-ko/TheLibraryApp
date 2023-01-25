package com.linhtetko.thelibraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import kotlinx.android.synthetic.main.viewpod_rating_progress_number.view.*

class RatingProgressNumberViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    fun setUpContent(starNum: Int, progress: Double){
        tvRatingProgressNum.text = starNum.toString()
        pgRatingProgress.setProgressPercentage(progress)
    }
}