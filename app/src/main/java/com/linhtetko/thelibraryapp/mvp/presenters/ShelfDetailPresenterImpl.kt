package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.mvp.views.ShelfDetailView
import com.linhtetko.thelibraryapp.mvp.views.YourBookView
import kotlin.text.Typography.section

class ShelfDetailPresenterImpl : ViewModel(), ShelfDetailPresenter {

    private val theLibraryModel: TheLibraryModel = TheLibraryModelImpl
    private var chips: MutableList<ChipVO> = mutableListOf()
    private var mView: ShelfDetailView? = null

    override fun getBookByShelf(id: Int, owner: LifecycleOwner) {
        theLibraryModel.getBooksByShelf(id)?.observe(owner){
            mView?.showBooks(it)
        }

        theLibraryModel.countBookByShelf(id)?.observe(owner) {
            mView?.bindForBookCount(it)
        }
        theLibraryModel.getShelf(id)?.observe(owner) {
            if (it != null)
                mView?.bindForShelfName(it.title)
        }
    }

    override fun deleteBookFromLibrary(title: String, author: String) {
        theLibraryModel.deleteBook(title, author)
    }

    override fun deleteShelf(id: Int) {
        theLibraryModel.deleteShelf(id)
    }

    override fun renameShelf(id: Int, name: String) {
        theLibraryModel.renameShelf(id, name)
    }

    override fun initView(view: ShelfDetailView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        listOf("Not Started", "Progress").forEach {
            chips.add(ChipVO(name = it))
            mView?.showSectionTitles(chips)
        }
    }

    override fun onClickBookItem(json: String) {
        val vos = Gson().fromJson<BookVO>(json, object : TypeToken<BookVO>() {}.type)
        mView?.navigateToBookDetail(vos.title, vos.author)
    }

    override fun onClickBookMore(json: String) {
        mView?.onTapBookMore(json)
    }

    override fun onSelect(name: String) {
        val tempChip = arrayListOf<ChipVO>()

        chips.distinctBy { it.name }
            .forEach {
                if (it.name == name) {
                    it.isSelected = !it.isSelected
                    tempChip.add(it)
                } else {
                    tempChip.add(it)
                }
            }

        tempChip.sortBy { it.isSelected }

        tempChip.reverse()
        if (chips.any { it.isSelected } and !(chips.any { it.type == ChipVO.CROSS })) {
            tempChip.add(0, ChipVO(name = "", type = ChipVO.CROSS))
        }

        mView?.showSectionTitles(tempChip)

    }

    override fun onClose() {

        val result = theLibraryModel.getBooksOneTime()
        mView?.showBooks(result)

        val tempChips = chips.toMutableList()
        tempChips.removeAll { it.type == ChipVO.CROSS }
//        chips = tempChips.map {
//            it.isSelected = false
//            it
//        }
        mView?.showSectionTitles(chips)

    }

    override fun onTapBack() {
        mView?.onBack()
    }
}