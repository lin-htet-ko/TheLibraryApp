package com.linhtetko.thelibraryapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.adapters.LibraryFilterChipAdapter
import com.linhtetko.thelibraryapp.bottomsheets.SortByFragment
import com.linhtetko.thelibraryapp.bottomsheets.ViewAsFragment
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.delegates.MainLibraryCommunicationDelegate
import com.linhtetko.thelibraryapp.enum.SortByEnum
import com.linhtetko.thelibraryapp.mvp.presenters.YourBookPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.YourBookPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.YourBookView
import com.linhtetko.thelibraryapp.views.viewpods.BookFilterAndSortViewPod
import kotlinx.android.synthetic.main.fragment_your_book.*

class YourBookFragment : Fragment(), YourBookView {


    private lateinit var mPresenter: YourBookPresenter
    private lateinit var mMainLibraryCommunicationDelegate: MainLibraryCommunicationDelegate

    private var bookFilterAndSortViewPod: BookFilterAndSortViewPod? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mMainLibraryCommunicationDelegate = context as MainLibraryCommunicationDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpViewPods()
        setUpCategoryAdapter()
        setUpBookAdapter()
        setUpListener()

        mPresenter.onUiReady(viewLifecycleOwner)
        bookFilterAndSortViewPod?.setUpYourSortButtonText(
            getString(
                R.string.lbl_sort_by_s,
                "Recent"
            )
        )
    }

    private fun setUpViewPods() {
        bookFilterAndSortViewPod = vpYourBookFilterAndSort as? BookFilterAndSortViewPod
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[YourBookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListener() {
        bookFilterAndSortViewPod?.setUpViewAsButtonListener {
            showViewsAsBottomSheet()
        }
        bookFilterAndSortViewPod?.setUpSortButtonListener {
            showSortBottomSheet()
        }
    }

    private fun showSortBottomSheet() {
        val fragment = SortByFragment()
        fragment.setUpSortByDelegate(this)
        fragment.show(childFragmentManager, "SortBy")
    }

    override fun showBooks(books: List<BookVO>?) {
        if (books != null) {
            bookFilterAndSortViewPod?.setUpNewBooks(books)
        }
    }

    override fun showSectionTitles(sections: List<ChipVO>?) {
        sections?.let {
            bookFilterAndSortViewPod?.setUpChips(it)
        }
    }

    override fun onTapBookMore(bookVO: String) {
        mMainLibraryCommunicationDelegate.showBookActions(bookVO)
    }

    override fun navigateToBookDetail(title: String, author: String) {
        mMainLibraryCommunicationDelegate.navigateToBooDetail(title, author)
    }

    private fun showViewsAsBottomSheet() {
        val fragment = ViewAsFragment()
        fragment.setUpViewAsDelegate(this)
        fragment.show(childFragmentManager, "ViewAs")
    }

    private fun setUpCategoryAdapter() {
        bookFilterAndSortViewPod?.setUpCategoryAdapter(LibraryFilterChipAdapter(mPresenter))
    }

    private fun setUpBookAdapter() {
        bookFilterAndSortViewPod?.setUpBookAdapter(BookAdapter(mPresenter))
    }

    override fun showError(message: String) {

    }

    override fun onBack() {

    }

    override fun viewAs(type: Int) {
        val viewType = when (type) {
            BookAdapter.LIST_VIEW -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(LinearLayoutManager(requireContext()))
                BookAdapter.LIST_VIEW
            }
            BookAdapter.GRID_SMALL_VIEW -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(
                    GridLayoutManager(
                        requireContext(),
                        3
                    )
                )
                BookAdapter.GRID_SMALL_VIEW
            }
            BookAdapter.GRID_LARGE_VIEW -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(
                    GridLayoutManager(
                        requireContext(),
                        2
                    )
                )
                BookAdapter.GRID_LARGE_VIEW
            }
            else -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(LinearLayoutManager(requireContext()))
                BookAdapter.LIST_VIEW
            }
        }

        bookFilterAndSortViewPod?.setUpNewBooks(
            bookFilterAndSortViewPod?.getBooks()?.map {
                it.viewType = viewType
                it
            } ?: listOf()
        )
    }

    override fun sortBy(type: SortByEnum) {
        val books = bookFilterAndSortViewPod?.getBooks()?.toMutableList()
        val booksAfterOperation = when (type) {
            SortByEnum.RECENT -> {
                bookFilterAndSortViewPod?.setUpYourSortButtonText(
                    getString(
                        R.string.lbl_sort_by_s,
                        "Recent"
                    )
                )
                books?.reversed()
            }
            SortByEnum.TITLE -> {
                bookFilterAndSortViewPod?.setUpYourSortButtonText(
                    getString(
                        R.string.lbl_sort_by_s,
                        "Title"
                    )
                )
                books?.sortBy {
                    it.title
                }

                books
            }
            SortByEnum.AUTHOR -> {
                bookFilterAndSortViewPod?.setUpYourSortButtonText(
                    getString(
                        R.string.lbl_sort_by_s,
                        "Author"
                    )
                )
                books?.sortBy {
                    it.author
                }

                books
            }
        }
        bookFilterAndSortViewPod?.setUpNewBooks(
            booksAfterOperation ?: listOf()
        )

    }

}