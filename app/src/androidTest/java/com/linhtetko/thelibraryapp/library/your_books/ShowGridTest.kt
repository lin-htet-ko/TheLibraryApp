package com.linhtetko.thelibraryapp.library.your_books

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.activities.MainActivity
import com.linhtetko.thelibraryapp.views.viewholders.BookLinearViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.BookSectionDetailViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.BookSectionViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.BookViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ShowGridTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.menuLibrary)).perform(click())

        onView(withId(R.id.ivYourBookViewAs)).perform(click())
    }

    @Test
    fun show_small_grid(){
        onView(withId(R.id.rdbViewAsSmallGrid)).perform(click())

        onView(withId(R.id.rvYourBooks)).check { view, noViewFoundException ->
            val viewHolder = (view as RecyclerView).findViewHolderForAdapterPosition(0)
            assert(viewHolder is BookViewHolder)
        }
    }
    @Test
    fun show_list(){
        onView(withId(R.id.rdbViewAsList)).perform(click())

        onView(withId(R.id.rvYourBooks)).check { view, noViewFoundException ->
            val viewHolder = (view as RecyclerView).findViewHolderForAdapterPosition(0)
            assert(viewHolder is BookLinearViewHolder)
        }
    }
    @Test
    fun show_large_grid(){
        onView(withId(R.id.rdbViewAsLargeGrid)).perform(click())

        onView(withId(R.id.rvYourBooks)).check { view, noViewFoundException ->
            val viewHolder = (view as RecyclerView).findViewHolderForAdapterPosition(0)
            assert(viewHolder is BookSectionDetailViewHolder)
        }
    }

}