package com.linhtetko.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding4.widget.textChanges
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.LibraryViewPagerAdapter
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.fragments.SearchBooksFragment
import com.linhtetko.thelibraryapp.fragments.SearchEbookFragment
import com.linhtetko.thelibraryapp.mvp.presenters.SearchPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.SearchPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.SearchView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity(), SearchView {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SearchActivity::class.java)
            return intent
        }
    }

    private lateinit var mSearchPresenter: SearchPresenter
    private val tabs by lazy {
        mapOf(
            getString(R.string.lbl_book) to SearchBooksFragment(),
            getString(R.string.lbl_ebooks) to SearchEbookFragment(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUpPresenter()
        setUpActionbar()
        setUpTabs()
        listenTextChanges()
        mSearchPresenter.onUiReady(this)
    }

    private fun setUpTabs() {
        vpSearch.adapter = LibraryViewPagerAdapter(
            tabs.values.toList(),
            supportFragmentManager,
            lifecycle
        )
        vpSearch.isSaveEnabled = false

        TabLayoutMediator(tlSearch, vpSearch) { tab, position ->
            tab.text = tabs.keys.toList()[position]
        }.attach()
    }

    private fun setUpActionbar() {
        setSupportActionBar(toolbarSearch)
    }

    private fun listenTextChanges() {
        etSearch.textChanges()
            .debounce(5, TimeUnit.MILLISECONDS)
            .flatMap {
                mSearchPresenter.search(it.toString())
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isEmpty()){
                        tvSearchResultFromPlay.visibility = View.INVISIBLE
                        tlSearch.visibility = View.INVISIBLE
                    }else{
                        tvSearchResultFromPlay.visibility = View.VISIBLE
                        tlSearch.visibility = View.VISIBLE
                    }
                    val books = it.map { book -> BookVO.toBookVo(book) }
                    mSearchPresenter.books.postValue(books)
                }, {
                    showError(it.localizedMessage ?: "")
                }
            )
    }

    private fun setUpPresenter() {
        mSearchPresenter = ViewModelProvider(this)[SearchPresenterImpl::class.java]
        mSearchPresenter.initView(this)
    }

    override fun navigateToBookDetail(jsonBook: String) {
        startActivity(BookDetailActivity.newIntent(this, jsonBook))
    }

    override fun showError(message: String) {

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