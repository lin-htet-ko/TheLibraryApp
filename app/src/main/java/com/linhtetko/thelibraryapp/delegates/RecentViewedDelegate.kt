package com.linhtetko.thelibraryapp.delegates

interface RecentViewedDelegate {

    fun onClickOfflineMode(title: String, author: String)
    fun onClickMore(title: String, author: String)
    fun onClickRecentViewedItem(title: String, author: String)
}