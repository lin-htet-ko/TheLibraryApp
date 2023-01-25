package com.linhtetko.thelibraryapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.bottomsheets.BookOverviewFragment
import com.linhtetko.thelibraryapp.bottomsheets.SectionDetailOverviewFragment
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.fragments.HomeFragment
import com.linhtetko.thelibraryapp.fragments.LibraryFragment
import com.linhtetko.thelibraryapp.mvp.presenters.MainPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.MainPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.MainView
import com.linhtetko.thelibraryapp.views.viewpods.SearchbarViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainPresenter: MainPresenter

    private val homeFragment by lazy { HomeFragment() }
    private val libraryFragment by lazy { LibraryFragment() }
    private var bookOverviewFragment: BookOverviewFragment? = null
    private var sectionDetailFragment: SectionDetailOverviewFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpActionbar()
        replaceFragment(homeFragment)
        setUpBottomNavigationBar()
        setUpPresenter()
        setUpViewPods()
    }


    private fun setUpBottomNavigationBar() {
        bnvMain.setOnItemSelectedListener { item ->
            return@setOnItemSelectedListener when (item.itemId) {
                R.id.menuLibrary -> {
                    replaceFragment(libraryFragment)
                    true
                }
                else -> {
                    replaceFragment(homeFragment)
                    true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcvMain, fragment).commit()
    }

    private fun setUpActionbar() {
        setSupportActionBar(tblMain)
    }


    private fun setUpPresenter() {
        mainPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mainPresenter.initView(this)
        mainPresenter.onUiReady(this)
    }

    private fun setUpViewPods() {
        (vpSearchbar as SearchbarViewPod).setUpDelegate(mainPresenter)
    }

    override fun showBookOverviewBottomSheet(bookVO: BookVO?) {
        if (bookVO != null) {
            bookOverviewFragment = BookOverviewFragment()
            bookOverviewFragment?.arguments = Bundle().apply {
                val json = Gson().toJson(bookVO)
                putString(BookOverviewFragment.BE_BOOK, json)
            }
            bookOverviewFragment?.show(supportFragmentManager, "BookOverview")
        }
    }

    override fun navigateToSearchScreen() {
        startActivity(SearchActivity.newIntent(this))
    }


    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onBack() {

    }

    override fun onStartSectionDetail(sectionName: String) {
        startActivity(SectionDetailActivity.newIntent(this, sectionName))
    }

    override fun deleteFromLibrary(title: String, author: String) {
        mainPresenter.deleteBookFromLibrary(title, author)
        bookOverviewFragment?.dismiss()
    }

    override fun onTapRecentBookMore(title: String, author: String) {
        mainPresenter.getBook(title, author, this)
    }

    override fun navigateToBookDetail(title: String, author: String) {
        startActivity(BookDetailActivity.newIntent(this, title, author))
    }

    override fun navigateToBookDetail(json: String) {
        startActivity(BookDetailActivity.newIntent(this, json))
    }

    override fun onTapSectionBookMoreAction(json: String) {
        sectionDetailFragment = SectionDetailOverviewFragment()
        sectionDetailFragment?.arguments = Bundle().apply {
            putString(SectionDetailOverviewFragment.BE_BOOK, json)
        }
        sectionDetailFragment?.show(supportFragmentManager, "BookDetailOverview")
    }

    override fun onTapAboutBook(bookVO: String) {
        startActivity(BookDetailActivity.newIntent(this, bookVO))
        sectionDetailFragment?.dismiss()
    }

    override fun addToShelves(title: String, author: String) {
        startActivity(AddToShelvesActivity.newIntent(this, title, author))
    }

    override fun showBookActions(bookVO: String) {
        val vos = Gson().fromJson<BookVO>(bookVO, object : TypeToken<BookVO>() {}.type)
        showBookOverviewBottomSheet(vos)
    }

    override fun navigateToBooDetail(title: String, author: String) {
        navigateToBookDetail(title, author)
    }

    override fun navigateToCreateShelf() {
        startActivity(CreateShelfActivity.newIntent(this))
    }

    override fun navigateToShelfDetail(id: Int) {
        startActivity(ShelfDetailActivity.newIntent(this, id))
    }
}