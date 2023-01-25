package com.linhtetko.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.adapters.LibraryFilterChipAdapter
import com.linhtetko.thelibraryapp.bottomsheets.BookOverviewFragment
import com.linhtetko.thelibraryapp.bottomsheets.ShelfActionsFragment
import com.linhtetko.thelibraryapp.bottomsheets.SortByFragment
import com.linhtetko.thelibraryapp.bottomsheets.ViewAsFragment
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.enum.SortByEnum
import com.linhtetko.thelibraryapp.mvp.presenters.ShelfDetailPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.ShelfDetailPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.ShelfDetailView
import com.linhtetko.thelibraryapp.views.viewpods.BookFilterAndSortViewPod
import kotlinx.android.synthetic.main.activity_shelf_detail.*

class ShelfDetailActivity : AppCompatActivity(), ShelfDetailView {

    companion object {
        const val IE_SHELF_ID = "shelf_id"
        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, ShelfDetailActivity::class.java)
            intent.putExtra(IE_SHELF_ID, id)
            return intent
        }
    }

    private var bookFilterAndSortViewPod: BookFilterAndSortViewPod? = null
    private lateinit var mPresenter: ShelfDetailPresenter
    private var bookOverviewFragment: BookOverviewFragment? = null
    private var id: Int? = null
    private var title: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelf_detail)

        setUpActionbar()
        setUpPresenter()
        setUpViewPods()
        setUpCategoryAdapter()
        setUpBookAdapter()
        setUpListener()

        getData()
        mPresenter.onUiReady(this)
        bookFilterAndSortViewPod?.setUpYourSortButtonText(
            getString(
                R.string.lbl_sort_by_s,
                "Recent"
            )
        )
    }

    private fun setUpActionbar() {
        setSupportActionBar(tlShelfDetail)
    }

    private fun getData() {
        id = intent.getIntExtra(IE_SHELF_ID, 0)
        if (id != 0){
            mPresenter.getBookByShelf(id!!, this)
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ShelfDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpViewPods() {
        bookFilterAndSortViewPod = vpShelfDetail as? BookFilterAndSortViewPod
    }

    private fun setUpListener() {
        bookFilterAndSortViewPod?.setUpViewAsButtonListener {
            showViewsAsBottomSheet()
        }
        bookFilterAndSortViewPod?.setUpSortButtonListener {
            showSortBottomSheet()
        }
        btnShelfDetail.setOnClickListener {
            showShelfActionBottomSheet()
        }
    }

    private fun showShelfActionBottomSheet() {
        val fragment = ShelfActionsFragment()
        fragment.arguments = Bundle().apply {
            putString(ShelfActionsFragment.BE_SHELF_TITLE, title)
        }
        fragment.show(supportFragmentManager, "ShelfActions")
    }

    private fun showSortBottomSheet() {
        val fragment = SortByFragment()
        fragment.setUpSortByDelegate(this)
        fragment.show(supportFragmentManager, "SortBy")
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
        bookOverviewFragment = BookOverviewFragment()
        bookOverviewFragment?.arguments = Bundle().apply {
            putString(BookOverviewFragment.BE_BOOK, bookVO)
        }
        bookOverviewFragment?.show(supportFragmentManager, "BookOverview")
    }

    override fun navigateToBookDetail(title: String, author: String) {
        startActivity(BookDetailActivity.newIntent(this, title, author))
    }

    override fun navigateToBookDetail(json: String) {
        
    }

    override fun bindForBookCount(count: Int?) {
        if (count != null) {
            tvShelfDetailBookCount.text = "$count Book${if (count<=0) "" else "s"}"
        }
    }

    override fun bindForShelfName(title: String) {
        this.title = title
        tvShelfDetailName.text = title
    }

    private fun showViewsAsBottomSheet() {
        val fragment = ViewAsFragment()
        fragment.setUpViewAsDelegate(this)
        fragment.show(supportFragmentManager, "ViewAs")
    }

    private fun setUpCategoryAdapter() {
        bookFilterAndSortViewPod?.setUpCategoryAdapter(LibraryFilterChipAdapter(mPresenter))
    }

    private fun setUpBookAdapter() {
        bookFilterAndSortViewPod?.setUpBookAdapter(BookAdapter(mPresenter))
    }

    override fun showError(message: String) {

    }

    override fun viewAs(type: Int) {
        val viewType = when (type) {
            BookAdapter.LIST_VIEW -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(LinearLayoutManager(this))
                BookAdapter.LIST_VIEW
            }
            BookAdapter.GRID_SMALL_VIEW -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(
                    GridLayoutManager(
                        this,
                        3
                    )
                )
                BookAdapter.GRID_SMALL_VIEW
            }
            BookAdapter.GRID_LARGE_VIEW -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(
                    GridLayoutManager(
                        this,
                        2
                    )
                )
                BookAdapter.GRID_LARGE_VIEW
            }
            else -> {
                bookFilterAndSortViewPod?.setBooksLayoutManager(LinearLayoutManager(this))
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

    override fun onStartSectionDetail(sectionName: String) {
        
    }

    override fun deleteFromLibrary(title: String, author: String) {
        mPresenter.deleteBookFromLibrary(title, author)
        bookOverviewFragment?.dismiss()
    }

    override fun onTapRecentBookMore(title: String, author: String) {
        
    }

    override fun onTapSectionBookMoreAction(json: String) {
        
    }

    override fun onTapAboutBook(bookVO: String) {
        
    }

    override fun addToShelves(title: String, author: String) {
        startActivity(AddToShelvesActivity.newIntent(this, title, author))
    }

    override fun renameShelf() {
        startActivity(id?.let { CreateShelfActivity.newIntent(this, it) })
    }

    override fun deleteShelf() {
        id?.let { mPresenter.deleteShelf(it) }
        super.onBackPressed()
    }

    override fun onBack() {
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}