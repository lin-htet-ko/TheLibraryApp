package com.linhtetko.thelibraryapp.delegates

import com.linhtetko.thelibraryapp.enum.SortByEnum

interface SortByDelegate {

    fun sortBy(type: SortByEnum)
}