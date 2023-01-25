package com.linhtetko.thelibraryapp.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.linhtetko.thelibraryapp.data.vos.ShelfVO

@Dao
interface ShelvesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelf(shelfVO: ShelfVO)

    @Query("SELECT * FROM shelf")
    fun getShelves(): LiveData<List<ShelfVO>>

    @Query("SELECT * FROM shelf WHERE _id=:id")
    fun getShelf(id: Int): LiveData<ShelfVO>

    @Query("SELECT * FROM shelf WHERE _id=:id")
    fun getShelfOneTime(id: Int): ShelfVO?


    @Query("DELETE FROM shelf WHERE _id=:id")
    fun delete(id: Int)

    @Query("UPDATE shelf SET title=:name WHERE _id=:id")
    fun rename(id: Int, name: String)
}