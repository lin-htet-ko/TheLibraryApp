package com.linhtetko.thelibraryapp.bottomsheets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.delegates.ShelfDelegate
import kotlinx.android.synthetic.main.fragment_shelf_actions.*

class ShelfActionsFragment : BottomSheetDialogFragment() {

    private var mShelfDelegate: ShelfDelegate? = null

    companion object {
        const val BE_SHELF_TITLE = "shelf_title"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mShelfDelegate = context as? ShelfDelegate
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shelf_actions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setUpListener()
    }

    private fun getData() {
        arguments?.getString(BE_SHELF_TITLE)?.let {
            tvShelfTitle.text = it
        }
    }

    private fun setUpListener() {
        llShelfActionDelete.setOnClickListener {
            mShelfDelegate?.deleteShelf()
            dismiss()
        }
        llShelfActionEdit.setOnClickListener {
            mShelfDelegate?.renameShelf()
            dismiss()
        }
    }

}