package com.linhtetko.thelibraryapp.bottomsheets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.delegates.MainHomeCommunicationDelegate
import com.linhtetko.thelibraryapp.views.viewpods.BookOverviewViewPod
import kotlinx.android.synthetic.main.fragment_book_overview.*

class BookOverviewFragment : BottomSheetDialogFragment() {

    private var mVpBookOverview: BookOverviewViewPod? = null
    private lateinit var mainHomeCommunicationDelegate: MainHomeCommunicationDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainHomeCommunicationDelegate = context as MainHomeCommunicationDelegate
    }

    companion object {
        const val BE_BOOK = "book_overview_object"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(BE_BOOK)?.let {
            val vos = Gson().fromJson<BookVO>(it, object : TypeToken<BookVO>() {}.type)
            setUpViewPod(vos)

            llBookDetailOverviewDelete.setOnClickListener {
                dismiss()
                mainHomeCommunicationDelegate.deleteFromLibrary(vos.title, vos.author)
            }
            llBookDetailOverviewAddToShelves.setOnClickListener {
                dismiss()
                mainHomeCommunicationDelegate.addToShelves(vos.title, vos.author)

            }
        }

    }

    private fun setUpViewPod(book: BookVO) {
        mVpBookOverview = (vpBookOverview as BookOverviewViewPod)
        mVpBookOverview?.setUpContent(book.title, book.author, book.bookImage ?: "", "EBoook")
    }

}