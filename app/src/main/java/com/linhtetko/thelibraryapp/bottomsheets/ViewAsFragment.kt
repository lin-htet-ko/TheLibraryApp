package com.linhtetko.thelibraryapp.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.delegates.ViewAsDelegate
import kotlinx.android.synthetic.main.fragment_view_as.*

class ViewAsFragment : BottomSheetDialogFragment() {

    private var viewAsDelegate: ViewAsDelegate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_as, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListener()
    }

    private fun setUpListener() {
        rdbViewAsList.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                viewAsDelegate?.viewAs(BookAdapter.LIST_VIEW)
            }
            dismiss()
        }
        rdbViewAsSmallGrid.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                viewAsDelegate?.viewAs(BookAdapter.GRID_SMALL_VIEW)
            }
            dismiss()
        }
        rdbViewAsLargeGrid.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                viewAsDelegate?.viewAs(BookAdapter.GRID_LARGE_VIEW)
            }
            dismiss()
        }
    }

    fun setUpViewAsDelegate(delegate: ViewAsDelegate){
        viewAsDelegate = delegate
    }

}