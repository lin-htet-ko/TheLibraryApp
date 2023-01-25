package com.linhtetko.thelibraryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.BookAdapter
import com.linhtetko.thelibraryapp.adapters.LibraryFilterChipAdapter
import com.linhtetko.thelibraryapp.adapters.LibraryViewPagerAdapter
import com.linhtetko.thelibraryapp.bottomsheets.ViewAsFragment
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.mvp.presenters.LibraryPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.LibraryPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.LibraryView
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment(), LibraryView {

    private val tabs by lazy {
        mapOf<String, Fragment>(
            getString(R.string.lbl_your_books) to YourBookFragment(),
            getString(R.string.lbl_your_shelves) to YourShelvesFragment()
        )
    }

    private lateinit var mPresenter: LibraryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpTabs()
        mPresenter.onUiReady(viewLifecycleOwner)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[LibraryPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpTabs() {

        vpLibraryFilter.adapter = LibraryViewPagerAdapter(tabs.values.toList(), childFragmentManager, lifecycle)
        vpLibraryFilter.isSaveEnabled = false
        vpLibraryFilter.isUserInputEnabled = false

        TabLayoutMediator(tlLibrary, vpLibraryFilter){tab, position ->
            tab.text = tabs.keys.toList()[position]
        }.attach()
    }


    override fun showError(message: String) {

    }

    override fun onBack() {

    }

}