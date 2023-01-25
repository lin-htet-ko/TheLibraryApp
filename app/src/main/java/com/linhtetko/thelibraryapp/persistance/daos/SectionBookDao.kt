package com.linhtetko.thelibraryapp.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO

@Dao
interface SectionBookDao {

    @Query("SELECT * FROM book_sections")
    fun getOverviewOfBooks(): LiveData<List<SectionBookVO>>

    @Query("SELECT * FROM book_sections WHERE list_name=:sectionName")
    fun getSectionDetail(sectionName: String): LiveData<SectionBookVO>

    @Query("SELECT * FROM book_sections WHERE list_name=:sectionName")
    fun getSectionDetailOneTime(sectionName: String): SectionBookVO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSectionBooks(sections: List<SectionBookVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSectionBook(section: SectionBookVO)
}