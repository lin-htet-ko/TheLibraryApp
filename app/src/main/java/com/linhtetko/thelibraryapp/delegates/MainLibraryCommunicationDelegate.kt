package com.linhtetko.thelibraryapp.delegates

import com.linhtetko.thelibraryapp.data.vos.BookVO

interface MainLibraryCommunicationDelegate {

    fun showBookActions(bookVO: String)
    fun navigateToBooDetail(title: String, author: String)
    fun navigateToCreateShelf()
    fun navigateToShelfDetail(id: Int)
}