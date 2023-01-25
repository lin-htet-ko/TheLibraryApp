package com.linhtetko.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.ReviewAdapter
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.mvp.presenters.BookDetailPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.BookDetailPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.BookDetailView
import com.linhtetko.thelibraryapp.views.viewpods.RatingProgressNumberViewPod
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity(), BookDetailView {

    companion object{
        const val IE_BOOK = "book_detail"
        const val IE_BOOK_TITLE = "book_title"
        const val IE_BOOK_AUTHOR = "book_author"

        fun newIntent(context: Context, json: String): Intent{
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(IE_BOOK, json)
            return intent
        }

        fun newIntent(context: Context, title: String, author: String): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            val bundle = Bundle().apply {
                putString(IE_BOOK_TITLE, title)
                putString(IE_BOOK_AUTHOR, author)
            }
            intent.putExtras(bundle)
            return intent
        }
    }

    private lateinit var mBookDetailPresenter: BookDetailPresenter
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        setUpPresenter()
        setUpActionBar()
        setUpViewPods()
        setUpReviewAdapter()
        getData()
    }

    private fun setUpViewPods() {
        (vpRatingNum5 as RatingProgressNumberViewPod).setUpContent(5, 30.0)
        (vpRatingNum4 as RatingProgressNumberViewPod).setUpContent(4, 50.0)
        (vpRatingNum3 as RatingProgressNumberViewPod).setUpContent(3, 20.0)
        (vpRatingNum2 as RatingProgressNumberViewPod).setUpContent(2, 10.0)
        (vpRatingNum1 as RatingProgressNumberViewPod).setUpContent(1, 10.0)
    }

    private fun setUpReviewAdapter() {
        reviewAdapter = ReviewAdapter()
        rvReviews.adapter = reviewAdapter
    }

    private fun getData() {
        val json = intent.getStringExtra(IE_BOOK)
        json?.let { showBookData(it) }

        intent.extras?.apply {
            val (title, author) = getString(IE_BOOK_TITLE) to getString(IE_BOOK_AUTHOR)
            if (title != null && author != null){
                mBookDetailPresenter.getBook(title, author, this@BookDetailActivity)
            }
        }
    }

    private fun setUpPresenter() {
        mBookDetailPresenter = ViewModelProvider(this)[BookDetailPresenterImpl::class.java]
        mBookDetailPresenter.initView(this)
    }

    private fun setUpActionBar() {
        setSupportActionBar(tlBookDetail)
    }

    override fun showBookData(json: String?) {
        val vos = Gson().fromJson<BookVO>(json, object : TypeToken<BookVO>(){}.type)

        vos?.apply {

            mBookDetailPresenter.insertIntoRecentBooks(this)

            Glide.with(this@BookDetailActivity).load(bookImage).into(ivBookDetailImg)
            tvBookDetailName.text = title ?: ""
            tvBookDetailAuthor.text = author ?: ""

            tvBookDetailAboutBook.text = description ?: ""
            tvBookDetailPublished.text = (publisher ?: "")

            btnBookDetailAboutBook.setOnClickListener {
                startActivity(AboutBookActivity.newIntent(this@BookDetailActivity, title ?: "", description ?: ""))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showError(message: String) {

    }

    override fun onBack() {
        super.onBackPressed()
    }
}