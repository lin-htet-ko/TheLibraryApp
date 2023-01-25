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
import com.linhtetko.thelibraryapp.delegates.SectionDetailActivityAndFragmentCommunicationDelegate
import com.linhtetko.thelibraryapp.views.viewpods.BookOverviewViewPod
import kotlinx.android.synthetic.main.fragment_book_detail_overview.*

class SectionDetailOverviewFragment : BottomSheetDialogFragment() {

    companion object{
        const val BE_BOOK = "book_object"
    }

    private var communicateDelegate: SectionDetailActivityAndFragmentCommunicationDelegate? = null
    private var mainHomeCommunicationDelegate: MainHomeCommunicationDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicateDelegate = context as? SectionDetailActivityAndFragmentCommunicationDelegate
        mainHomeCommunicationDelegate = context as? MainHomeCommunicationDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBook()
    }

    private fun getBook() {
        arguments?.getString(BE_BOOK)?.apply {
            val vo = Gson().fromJson<BookVO>(this, object : TypeToken<BookVO>(){}.type)
            (vpBookDetailOverview as BookOverviewViewPod).setUpContent(
                title = vo.title ?: "",
                author = vo.author ?: "",
                imageUrl = vo.bookImage ?: "",
                type = "Ebook"
            )
            setUpListener(this)
        }
    }

    private fun setUpListener(bookVO: String) {
        llBookOverviewAboutBook.setOnClickListener {
            communicateDelegate?.onTapAboutBook(bookVO)
            mainHomeCommunicationDelegate?.onTapAboutBook(bookVO)
        }
    }

}