package com.linhtetko.thelibraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.linhtetko.thelibraryapp.delegates.SearchbarDelegate
import kotlinx.android.synthetic.main.viewpod_search_bar.view.*

class SearchbarViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    private var mSearchbarDelegate: SearchbarDelegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

        setUpListener()
    }

    private fun setUpListener() {
        llSearchbar.setOnClickListener {
            mSearchbarDelegate?.onSearchbarClick()
        }
        ivVpProfile.setOnClickListener {
            mSearchbarDelegate?.onProfileClick()
        }
    }

    fun setUpDelegate(delegate: SearchbarDelegate){
        mSearchbarDelegate = delegate
    }
}