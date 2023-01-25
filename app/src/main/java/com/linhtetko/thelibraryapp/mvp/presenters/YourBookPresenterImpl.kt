package com.linhtetko.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.ChipVO
import com.linhtetko.thelibraryapp.mvp.views.YourBookView

class YourBookPresenterImpl : ViewModel(), YourBookPresenter {

    private val theLibraryModel: TheLibraryModel = TheLibraryModelImpl
    private var chips: List<ChipVO> = listOf()
    private var mView: YourBookView? = null

    override fun initView(view: YourBookView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        theLibraryModel.getBooks()?.observe(owner) {
            mView?.showBooks(it.reversed())
        }
        theLibraryModel.getBooks()?.observe(owner) {
//            mView?.showBooks(it)
            chips = it.map { section -> ChipVO(section.listName ?: "") }.distinctBy { it.name }
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


        chips = tempChip.reversed()

        tempChip.reverse()
        if (chips.any { it.isSelected } and !(chips.any { it.type == ChipVO.CROSS })) {
            tempChip.add(0, ChipVO(name = "", type = ChipVO.CROSS))
        }

        mView?.showSectionTitles(tempChip)

        val books = mutableListOf<BookVO>()
        val selectedChips = chips.filter { it.isSelected }
        selectedChips.forEach {
            val result = theLibraryModel.getBooksBySection(it.name)
            if (!result.isNullOrEmpty()) {
                books.addAll(result)
            }
        }
        if (selectedChips.isEmpty()) {
            val result = theLibraryModel.getBooksOneTime()
            if (!result.isNullOrEmpty()) {
                books.addAll(result)
            }
        }
        mView?.showBooks(books)

    }

    override fun onClose() {

        val result = theLibraryModel.getBooksOneTime()
        mView?.showBooks(result)

        val tempChips = chips.toMutableList()
        tempChips.removeAll { it.type == ChipVO.CROSS }
        chips = tempChips.map {
            it.isSelected = false
            it
        }.reversed()
        mView?.showSectionTitles(chips)


    }

    override fun onTapBack() {
        mView?.onBack()
    }
}