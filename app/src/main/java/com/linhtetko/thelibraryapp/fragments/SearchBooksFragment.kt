package com.linhtetko.thelibraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.adapters.SearchBooksAdapter
import com.linhtetko.thelibraryapp.mvp.presenters.SearchPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.SearchPresenterImpl
import kotlinx.android.synthetic.main.fragment_search_books.*

class SearchBooksFragment : Fragment() {


    private lateinit var searchBookAdapter: SearchBooksAdapter
    private lateinit var searchPresenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpAdapter()
        observeBooks()
    }

    private fun setUpPresenter() {
        searchPresenter = ViewModelProvider(requireActivity())[SearchPresenterImpl::class.java]
    }

    private fun observeBooks() {
        searchPresenter.books.observe(requireActivity()){
            searchBookAdapter.setNewData(it)
        }
    }

    private fun setUpAdapter() {
        searchBookAdapter = SearchBooksAdapter(listOf(), searchPresenter)
        rvSearchBooks.adapter = searchBookAdapter
    }

}