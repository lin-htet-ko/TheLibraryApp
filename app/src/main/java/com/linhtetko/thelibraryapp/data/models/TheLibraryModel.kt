package com.linhtetko.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO

interface TheLibraryModel {

    fun getBookSections(onError: (String) -> Unit): LiveData<List<SectionBookVO>>?
    fun getSectionDetail(
        sectionName: String,
        onError: (String) -> Unit
    ): LiveData<SectionBookVO>?

    fun getSectionDetail(
        sectionName: String
    ): SectionBookVO?

    fun insertBook(book: BookVO)
    fun insertBooks(books: List<BookVO>)
    fun getBook(title: String, author: String):LiveData<BookVO>?
    fun getBooks(): LiveData<List<BookVO>>?
    fun getBooksOneTime(): List<BookVO>?
    fun getBooksBySection(sectionName: String): List<BookVO>?
    fun getBooksByShelf(id: Int): LiveData<List<BookVO>>?
    fun getBooksByShelfOneTime(id: Int): List<BookVO>?
    fun getLastBookByShelf(id: Int): LiveData<BookVO>?
    fun deleteBook(title: String, author: String)
    fun countBookByShelf(id: Int): LiveData<Int>?

    fun insertShelf(shelfVO: ShelfVO)
    fun getShelf(id: Int): LiveData<ShelfVO>?
    fun getShelves(): LiveData<List<ShelfVO>>?
    fun deleteShelf(id: Int)
    fun renameShelf(id: Int, name: String)
    fun getShelfOneTime(id: Int): ShelfVO?
    fun getBookOneTime(title: String, author: String): BookVO?

}