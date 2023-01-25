package com.linhtetko.thelibraryapp.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.thelibraryapp.data.vos.BookVO

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getBooks(): LiveData<List<BookVO>>

    @Query("SELECT * FROM books")
    fun getBooksOneTime(): List<BookVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(bookVO: BookVO)

    @Query("SELECT * FROM books WHERE title=:title AND author=:author")
    fun getBook(title: String, author: String): LiveData<BookVO>

    @Query("SELECT * FROM books WHERE list_name=:sectionName")
    fun getBookBySectionOneTime(sectionName: String): List<BookVO>

    @Query("DELETE FROM books WHERE title=:title AND author=:author")
    fun deleteBook(title: String, author: String)

    @Query("SELECT COUNT(*) FROM books WHERE self_id=:shelfId")
    fun countOfBookByShelf(shelfId: Int): LiveData<Int>

    @Query("SELECT * FROM books WHERE self_id=:shelfId")
    fun getBooksByShelf(shelfId: Int): LiveData<List<BookVO>>

    @Query("SELECT * FROM books WHERE self_id=:shelfId ORDER BY self_id DESC LIMIT 1")
    fun getLastBookByShelf(shelfId: Int): LiveData<BookVO>

    @Query("SELECT * FROM books WHERE title=:title AND author=:author")
    fun getBookOneTime(title: String, author: String): BookVO?

    @Query("SELECT * FROM books WHERE self_id=:id")
    fun getBooksByShelfOneTime(id: Int): List<BookVO>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<BookVO>)


}