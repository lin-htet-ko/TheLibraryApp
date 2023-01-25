package com.linhtetko.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.AddToShelvesAdapter
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.mvp.presenters.AddToShelvesPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.AddToShelvesPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.AddToShelvesView
import kotlinx.android.synthetic.main.activity_add_to_shelves.*

class AddToShelvesActivity : AppCompatActivity(), AddToShelvesView {

    companion object {
        const val IE_BOOK_TITLE = "book_object_title"
        const val IE_BOOK_AUTHOR = "book_object_author"

        fun newIntent(context: Context, title: String, author: String): Intent {
            val intent = Intent(context, AddToShelvesActivity::class.java)
            intent.putExtra(IE_BOOK_TITLE, title)
            intent.putExtra(IE_BOOK_AUTHOR, author)
            return intent
        }
    }

    private lateinit var mPresenter: AddToShelvesPresenter
    private lateinit var addToShelvesAdapter: AddToShelvesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelves)

        setUpPresenter()
        setUpActionBar()
        setUpAddToShelvesAdapter()
        setUpListener()
        getData()
        mPresenter.onUiReady(this)
    }

    private fun setUpAddToShelvesAdapter() {
        addToShelvesAdapter = AddToShelvesAdapter(addToShelfDelegate = mPresenter, owner = this)
        rvAddToShelves.adapter = addToShelvesAdapter
    }

    private fun setUpListener() {
        ivAddToShelves.setOnClickListener { mPresenter.onTapDone() }
    }

    private fun setUpActionBar() {
        setSupportActionBar(tlAddToShelves)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[AddToShelvesPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun getData() {
        val title = intent.getStringExtra(IE_BOOK_TITLE)
        val author = intent.getStringExtra(IE_BOOK_AUTHOR)
        if (!title.isNullOrEmpty() and !author.isNullOrEmpty()) {
            mPresenter.getBookOneTime(title ?: "", author ?: "")
        }
    }

    override fun operateBookVO(vo: BookVO) {

    }

    override fun showShelves(it: List<ShelfVO>?) {
        if (it != null) {
            addToShelvesAdapter.setUpNewData(it)
        }
    }

    override fun bindForShelfCount(count: Int?, tvShelfBookCount: AppCompatTextView) {
        count?.let {
            tvShelfBookCount.text = "$count Book${if (count<=0) "" else "s"}"
        }
    }

    override fun bindForShelfImage(string: String, ivShelf: ShapeableImageView) {
        string.let {
            Glide.with(this).load(it).into(ivShelf)
        }
    }

    override fun navigateToBack() {
        super.onBackPressed()
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