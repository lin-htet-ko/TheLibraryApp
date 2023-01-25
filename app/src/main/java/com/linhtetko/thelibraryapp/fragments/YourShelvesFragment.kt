package com.linhtetko.thelibraryapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.adapters.ShelvesAdapter
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.delegates.MainLibraryCommunicationDelegate
import com.linhtetko.thelibraryapp.mvp.presenters.YourShelvesPresenter
import com.linhtetko.thelibraryapp.mvp.presenters.YourShelvesPresenterImpl
import com.linhtetko.thelibraryapp.mvp.views.YourShelvesView
import kotlinx.android.synthetic.main.fragment_your_shelves.*

class YourShelvesFragment : Fragment(), YourShelvesView {

    private lateinit var mPresenter: YourShelvesPresenter
    private var shelvesAdapter: ShelvesAdapter? = null
    private lateinit var mainLibraryCommunicationDelegate: MainLibraryCommunicationDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainLibraryCommunicationDelegate = context as MainLibraryCommunicationDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_shelves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpShelvesAdapter()
        setUpListener()

        mPresenter.onUiReady(viewLifecycleOwner)
    }

    private fun setUpShelvesAdapter() {
        shelvesAdapter = ShelvesAdapter(listOf(), mPresenter, viewLifecycleOwner)
        rvYourShelves.adapter = shelvesAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[YourShelvesPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListener() {
        fabCreateShelf.setOnClickListener {
            mPresenter.onTapCreateShelf()
        }
    }

    override fun navigateToCreateShelf() {
        mainLibraryCommunicationDelegate.navigateToCreateShelf()
    }

    override fun hideEmptyView() {
        ivYourShelvesEmpty.visibility = View.INVISIBLE
        tvYourShelvesEmptyTitle.visibility = View.INVISIBLE
        tvYourShelvesEmptySub.visibility = View.INVISIBLE
    }

    override fun showShelves(shelves: List<ShelfVO>?) {
        if (shelves != null) {
            shelvesAdapter?.setNewData(shelves)
        }
    }

    override fun showEmptyView() {
        ivYourShelvesEmpty.visibility = View.VISIBLE
        tvYourShelvesEmptyTitle.visibility = View.VISIBLE
        tvYourShelvesEmptySub.visibility = View.VISIBLE
    }

    override fun bindForShelfCount(count: Int?, tvShelfBookCount: AppCompatTextView) {
        count?.let {
            tvShelfBookCount.text = "$count Book${if (count<=0) "" else "s"}"
        }
    }

    override fun bindForShelfImage(bookImage: String?, ivShelf: ShapeableImageView) {
        bookImage?.let {
            Glide.with(requireContext()).load(it).into(ivShelf)
        }
    }

    override fun navigateToShelfDetail(id: Int) {
        mainLibraryCommunicationDelegate.navigateToShelfDetail(id)
    }

    override fun showError(message: String) {

    }

    override fun onBack() {

    }

}