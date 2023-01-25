package com.linhtetko.thelibraryapp.home

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.activities.MainActivity
import com.linhtetko.thelibraryapp.utils.first
import com.linhtetko.thelibraryapp.views.viewholders.BookSectionViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.BookViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class GoToBookDetailAndShowInCarouselTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun one_go_to_book_detail_and_show_in_carousel() {
        onView(withId(R.id.rvBookSections)).perform(RecyclerViewActions.actionOnItemAtPosition<BookSectionViewHolder>(0, scrollTo()))

        onView(first(withText("DESERT STAR"))).perform(click())

        onView(withContentDescription("Navigate up")).perform(click())

        onView(first(withId(R.id.ivRecentViewed))).check(matches(withContentDescription("DESERT STAR")))

    }
    @Test
    fun two_go_to_book_detail_and_show_in_carousel() {
        onView(withId(R.id.rvBookSections)).perform(RecyclerViewActions.actionOnItemAtPosition<BookSectionViewHolder>(1, scrollTo()))
        
        onView(first(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING"))).perform(click())

        onView(withContentDescription("Navigate up")).perform(click())

        onView(first(withId(R.id.ivRecentViewed))).check(matches(withContentDescription("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING")))

    }
    @Test
    fun three_go_to_book_detail_and_show_in_carousel() {
        onView(withId(R.id.rvBookSections)).perform(RecyclerViewActions.actionOnItemAtPosition<BookSectionViewHolder>(2, scrollTo()))
        onView(first(withText("THE BODY KEEPS THE SCORE"))).perform(click())
        onView(withContentDescription("Navigate up")).perform(click())

        onView(first(withId(R.id.ivRecentViewed))).check(matches(withContentDescription("THE BODY KEEPS THE SCORE")))

    }

}