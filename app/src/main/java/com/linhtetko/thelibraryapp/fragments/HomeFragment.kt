package com.linhtetko.thelibraryapp.fragments

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselView
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.activities.BookDetailActivity
import com.linhtetko.thelibraryapp.adapters.BookSectionAdapter
import com.linhtetko.thelibraryapp.adapters.RecentViewedBookAdapter
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.delegates.MainHomeCommunicationDelegate
import com.linhtetko.thelibraryapp.mvp.presenters.HomePresenter
import com.linhtetko.thelibraryapp.mvp.presenters.HomePresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeView {

    private lateinit var mRecentViewedBookAdapter: RecentViewedBookAdapter
    private lateinit var homePresenter: HomePresenter
    private lateinit var bookSectionAdapter: BookSectionAdapter
    private lateinit var carousel: Carousel
    private var mainHomeCommunicationDelegate: MainHomeCommunicationDelegate? = null

    private val versions by lazy {
        mapOf(
            0 to getString(R.string.lbl_ebooks),
            1 to getString(R.string.lbl_audio_books)
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainHomeCommunicationDelegate = context as? MainHomeCommunicationDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        setUpPresenter()
        setUpBookVersionTabs()
        setUpRecentViewed()
        setUpBookSectionAdapter()
        homePresenter.onUiReady(viewLifecycleOwner)
    }

    private fun setUpBookSectionAdapter() {
        bookSectionAdapter = BookSectionAdapter(homePresenter, homePresenter)
        rvBookSections.adapter = bookSectionAdapter
    }

    private fun setUpBookVersionTabs() {
        versions.values.forEach {
            val tab = tlBookVersion.newTab()
            tab.text = it
            tlBookVersion.addTab(tab)

        }
        tlBookVersion.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setUpRecentViewed() {
        mRecentViewedBookAdapter = RecentViewedBookAdapter(homePresenter, listOf())
//        rvRecentViewed.adapter = mRecentViewedBookAdapter

        carousel = Carousel(requireActivity() as AppCompatActivity, rvRecentViewed, mRecentViewedBookAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
//        carousel.autoScroll(true, 5000, true)
        carousel.scaleView(true)
    }

    private fun setUpPresenter() {
        homePresenter = ViewModelProvider(this)[HomePresenterImpl::class.java]
        homePresenter.initView(this)
    }

    override fun showEbooks(ebooks: List<SectionBookVO>?) {
        if (ebooks != null) {
            hideProgress()
            bookSectionAdapter.setSections(ebooks)
        }else{
            showProgress()
        }
    }

    override fun onTapBookMore(title: String, author: String) {
        mainHomeCommunicationDelegate?.onTapRecentBookMore(title, author)
    }

    override fun onTapSectionBookMore(sectionName: String) {
        mainHomeCommunicationDelegate?.onStartSectionDetail(sectionName)
    }

    override fun navigateToBookDetail(title: String, author: String) {
        mainHomeCommunicationDelegate?.navigateToBookDetail(title, author)
    }

    override fun navigateToBookDetail(json: String) {
        mainHomeCommunicationDelegate?.navigateToBookDetail(json)
    }

    override fun showRecentBooks(it: List<BookVO>?) {
        if(it != null){
            mRecentViewedBookAdapter.setNewData(it?.reversed() ?: listOf())
        }

    }

    override fun onTapSectionBookMoreAction(json: String) {
        mainHomeCommunicationDelegate?.onTapSectionBookMoreAction(json)
    }


    override fun showError(message: String) {

    }

    override fun onBack() {

    }

    private fun showProgress(){
        pbHome.visibility = View.VISIBLE
    }
    private fun hideProgress(){
        pbHome.visibility = View.INVISIBLE
    }

}