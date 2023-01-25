package com.linhtetko.thelibraryapp

import android.app.Application
import com.linhtetko.thelibraryapp.data.models.BaseModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModel
import com.linhtetko.thelibraryapp.data.models.TheLibraryModelImpl

class TheLibraryApp: Application() {

    override fun onCreate() {
        super.onCreate()

        TheLibraryModelImpl.initDatabase(applicationContext)

    }
}