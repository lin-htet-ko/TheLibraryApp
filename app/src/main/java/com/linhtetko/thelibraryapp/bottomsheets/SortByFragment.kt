package com.linhtetko.thelibraryapp.bottomsheets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.delegates.SortByDelegate
import com.linhtetko.thelibraryapp.enum.SortByEnum
import com.linhtetko.thelibraryapp.fragments.YourBookFragment
import kotlinx.android.synthetic.main.fragment_sort_by.*

class SortByFragment : BottomSheetDialogFragment() {

    private var mSortByDelegate: SortByDelegate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sort_by, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListener()
    }

    private fun setUpListener() {
        rdbSortByRecent.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked){
                mSortByDelegate?.sortBy(SortByEnum.RECENT)
            }
            dismiss()
        }
        rdbSortByTitle.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked){
                mSortByDelegate?.sortBy(SortByEnum.TITLE)
            }
            dismiss()
        }
        rdbSortByAuthor.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked){
                mSortByDelegate?.sortBy(SortByEnum.AUTHOR)
            }
            dismiss()
        }

    }

    fun setUpSortByDelegate(sortByDelegate: SortByDelegate) {
        mSortByDelegate = sortByDelegate
    }


}