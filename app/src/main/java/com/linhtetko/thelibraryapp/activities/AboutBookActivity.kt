package com.linhtetko.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.icu.util.IndianCalendar.IE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.linhtetko.thelibraryapp.R
import kotlinx.android.synthetic.main.activity_about_book.*

class AboutBookActivity : AppCompatActivity() {

    companion object{
        const val IE_ABOUT_BOOK_TITLE = "about_book_title"
        const val IE_ABOUT_BOOK_DESCRIPTION = "about_book_title"

        fun newIntent(context: Context, title: String, description: String): Intent{
            val intent = Intent(context, AboutBookActivity::class.java)
            intent.putExtra(IE_ABOUT_BOOK_TITLE, title)
            intent.putExtra(IE_ABOUT_BOOK_DESCRIPTION, description)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_book)

        setUpActionBar()
        bindData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindData() {
        supportActionBar?.title = intent.getStringExtra(IE_ABOUT_BOOK_TITLE) ?: ""
        tvAboutBook.text = intent.getStringExtra(IE_ABOUT_BOOK_DESCRIPTION) ?: ""
    }

    private fun setUpActionBar() {
        setSupportActionBar(tlAboutBook)
    }
}