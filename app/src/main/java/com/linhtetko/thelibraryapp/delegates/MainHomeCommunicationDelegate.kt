package com.linhtetko.thelibraryapp.delegates

interface MainHomeCommunicationDelegate {

    fun onStartSectionDetail(sectionName: String)
    fun deleteFromLibrary(title: String, author: String)
    fun onTapRecentBookMore(title: String, author: String)
    fun navigateToBookDetail(title: String, author: String)
    fun navigateToBookDetail(json: String)
    fun onTapSectionBookMoreAction(json: String)
    fun onTapAboutBook(bookVO: String)
    fun addToShelves(title: String, author: String)
}