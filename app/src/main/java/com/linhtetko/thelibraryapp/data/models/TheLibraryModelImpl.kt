package com.linhtetko.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.utils.ERROR_MESSAGE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object TheLibraryModelImpl : BaseModel(), TheLibraryModel {


    override fun getBookSections(onError: (String) -> Unit): LiveData<List<SectionBookVO>>? {
        theLibraryApi.getOverviewOfBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val sections =
                    it.results?.lists?.mapNotNull { sectionBookVO ->
                        sectionBookVO?.books =
                            sectionBookVO?.books?.map { book -> book?.copy(listName = sectionBookVO?.listName) }
                        sectionBookVO
                    }

                if (sections != null)
                    sectionBookDao?.insertSectionBooks(sections)

            }, {
                onError(it.localizedMessage ?: ERROR_MESSAGE)
            })

        return sectionBookDao?.getOverviewOfBooks()
    }

    override fun getSectionDetail(
        sectionName: String,
        onError: (String) -> Unit
    ): LiveData<SectionBookVO>? {
//        theLibraryApi.getSectionDetail(sectionName)
        return sectionBookDao?.getSectionDetail(sectionName)
    }

    override fun getSectionDetail(sectionName: String): SectionBookVO? {
        return sectionBookDao?.getSectionDetailOneTime(sectionName)
    }

    override fun insertBook(book: BookVO) {
        bookDao?.insertBook(book)
    }

    override fun insertBooks(books: List<BookVO>) {
        bookDao?.insertBooks(books)
    }

    override fun getBook(title: String, author: String): LiveData<BookVO>? {
        return bookDao?.getBook(title, author)
    }

    override fun getBooks(): LiveData<List<BookVO>>? {
        return bookDao?.getBooks()
    }

    override fun getBooksOneTime(): List<BookVO>? {
        return bookDao?.getBooksOneTime()
    }

    override fun getBooksBySection(sectionName: String): List<BookVO>? {
        return bookDao?.getBookBySectionOneTime(sectionName)
    }

    override fun getBooksByShelf(id: Int): LiveData<List<BookVO>>? {
        return bookDao?.getBooksByShelf(id)
    }

    override fun getBooksByShelfOneTime(id: Int): List<BookVO>? {
        return bookDao?.getBooksByShelfOneTime(id)
    }

    override fun getLastBookByShelf(id: Int): LiveData<BookVO>? {
        return bookDao?.getLastBookByShelf(id)
    }

    override fun deleteBook(title: String, author: String) {
        bookDao?.deleteBook(title, author)
    }

    override fun countBookByShelf(id: Int): LiveData<Int>? {
        return bookDao?.countOfBookByShelf(id)
    }

    override fun insertShelf(shelfVO: ShelfVO) {
        shelvesDao?.insertShelf(shelfVO)
    }

    override fun getShelf(id: Int): LiveData<ShelfVO>? {
        return shelvesDao?.getShelf(id)
    }

    override fun getShelves(): LiveData<List<ShelfVO>>? {
        return shelvesDao?.getShelves()
    }

    override fun deleteShelf(id: Int) {
        shelvesDao?.delete(id)
    }

    override fun renameShelf(id: Int, name: String) {
        shelvesDao?.rename(id, name)
    }

    override fun getShelfOneTime(id: Int): ShelfVO? {
        return shelvesDao?.getShelfOneTime(id)
    }

    override fun getBookOneTime(title: String, author: String): BookVO? {
        return bookDao?.getBookOneTime(title, author)
    }

}