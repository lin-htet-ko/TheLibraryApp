package com.linhtetko.thelibraryapp.utils

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers


fun <T> first(matcher: Matcher<T>): Matcher<T>{
    var first = true
    return object : BaseMatcher<T>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Return First Matching Item")
        }

        override fun matches(item: Any?): Boolean {
            if (first && matcher.matches(item)){
                first = false
                return true
            }
            return false
        }
    }
}
fun <T> second(matcher: Matcher<T>): Matcher<T>{
    var count = 0
    return object : BaseMatcher<T>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Return Second Matching Item")
        }

        override fun matches(item: Any?): Boolean {
            if (count == 2 && matcher.matches(item)){
                return true
            }
            count++

            return false
        }
    }
}
