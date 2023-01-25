package com.linhtetko.thelibraryapp.home

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.activities.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class EmptyCarouselTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun empty_carousel_when_no_book_view(){
        Espresso.onView(withId(R.id.rvRecentViewed)).check { view, noViewFoundException ->
            val recyclerView = view as RecyclerView
            assert(recyclerView.adapter?.itemCount == 0)
        }
    }
}