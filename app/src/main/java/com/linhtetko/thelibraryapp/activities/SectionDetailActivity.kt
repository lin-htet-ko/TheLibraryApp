package com.linhtetko.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.BookSectionDetailAdapter
import com.linhtetko.thelibraryapp.bottomsheets.SectionDetailOverviewFragment
import com.linhtetko.thelibraryapp.bottomsheets.SectionDetailOverviewFragment.Companion.BE_BOOK
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.mvp.presenters.SectionDetailPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.SectionDetailPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.SectionDetailView
import kotlinx.android.synthetic.main.activity_section_detail.*

class SectionDetailActivity : AppCompatActivity(), SectionDetailView {

    private lateinit var mSectionDetailPresenter: SectionDetailPresenter
    private lateinit var mSectionDetailAdapter: BookSectionDetailAdapter

    companion object {
        const val IE_SECTION_NAME = "section_name"
        fun newIntent(context: Context, sectionName: String): Intent {
            val intent = Intent(context, SectionDetailActivity::class.java)
            intent.putExtra(IE_SECTION_NAME, sectionName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_detail)

        setUpPresenter()
        setUpActionBar()
        setUpSectionDetailAdapter()
        getIntentArgs()
        mSectionDetailPresenter.onUiReady(this)
    }

    private fun getIntentArgs() {
        intent.getStringExtra(IE_SECTION_NAME)?.let { sectionName ->
            mSectionDetailPresenter.getSectionDetail(this, sectionName)
        }
    }

    private fun setUpSectionDetailAdapter() {
        mSectionDetailAdapter = BookSectionDetailAdapter(bookDelegate =  mSectionDetailPresenter)
        rvSectionDetailBooks.adapter = mSectionDetailAdapter
    }

    private fun setUpPresenter() {
        mSectionDetailPresenter = ViewModelProvider(this)[SectionDetailPresenterImpl::class.java]
        mSectionDetailPresenter.initView(this)

    }

    private fun setUpActionBar() {
        setSupportActionBar(tbBookSectionDetail)
    }

    override fun onTapBookSectionDetailMore(bookVO: String) {
        val fragment = SectionDetailOverviewFragment()
        fragment.arguments = Bundle().apply {
            putString(BE_BOOK, bookVO)
        }
        fragment.show(supportFragmentManager, "BookDetailOverview")
    }

    override fun showSectionDetailData(sectionBookVO: SectionBookVO) {
        supportActionBar?.title = sectionBookVO.listName ?: ""
        mSectionDetailAdapter.setNewData(sectionBookVO.books?.filterNotNull() ?: listOf())
    }

    override fun showError(message: String) {

    }

    override fun onTapAboutBook(bookString: String) {
        startActivity(BookDetailActivity.newIntent(this, bookString))
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