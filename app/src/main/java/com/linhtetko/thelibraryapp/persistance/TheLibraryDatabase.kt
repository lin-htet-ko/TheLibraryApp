package com.linhtetko.thelibraryapp.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.data.vos.ShelfVO
import com.linhtetko.thelibraryapp.persistance.daos.BookDao
import com.linhtetko.thelibraryapp.persistance.daos.SectionBookDao
import com.linhtetko.thelibraryapp.persistance.daos.ShelvesDao

@Database(entities = [BookVO::class, SectionBookVO::class, ShelfVO::class], version = 1)
abstract class TheLibraryDatabase : RoomDatabase() {
    companion object {

        private var instance: TheLibraryDatabase? = null

        fun getInstance(context: Context): TheLibraryDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, TheLibraryDatabase::class.java, "the_library_db")
                        .fallbackToDestructiveMigrationOnDowngrade()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance!!
        }
    }

    abstract fun sectionBookDao(): SectionBookDao
    abstract fun bookDao(): BookDao
    abstract fun shelvesDao(): ShelvesDao
}