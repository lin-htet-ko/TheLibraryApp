package com.linhtetko.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl.insertShelf
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.mvp.presenters.CreateShelvesPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.CreateShelvesPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.CreateShelvesView
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity(), CreateShelvesView {

    companion object{
        const val IE_SHELF_ID = "shelf_id"
        fun newIntent(context: Context): Intent{
            val intent = Intent(context, CreateShelfActivity::class.java)
            return intent
        }
        fun newIntent(context: Context, id: Int): Intent{
            val intent = Intent(context, CreateShelfActivity::class.java)
            intent.putExtra(IE_SHELF_ID, id)
            return intent
        }

    }

    private lateinit var mPresenter: CreateShelvesPresenter
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)

        setUpPresenter()
        getData()
        setUpActionBar()
        setUpListener()
        mPresenter.onUiReady(this)
    }

    private fun getData() {
        id = intent.getIntExtra(IE_SHELF_ID, 0)
        if (id != 0){
            mPresenter.getShelf(id!!)?.let {
                etCreateSelf.setText(it.title)
            }
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[CreateShelvesPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListener() {
        btnCreateShelf.setOnClickListener {
            insertNewShelf()
        }
    }

    private fun insertNewShelf() {
        val title = etCreateSelf.text.toString().trim()
        if (title.isEmpty()){
            etCreateSelf.error = getString(R.string.lbl_required)
            return
        }
        if (id == null || id == 0) {
            mPresenter.insertNewShelf(
                ShelfVO(title = title)
            )
        }else{
            mPresenter.updateShelf(id!!, title)
        }
        super.onBackPressed()
    }

    private fun setUpActionBar() {
        setSupportActionBar(tlCreateShelf)
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