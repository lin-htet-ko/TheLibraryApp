package com.linhtetko.thelibraryapp.library.your_books

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.activities.MainActivity
import com.linhtetko.thelibraryapp.utils.first
import com.linhtetko.thelibraryapp.views.viewholders.LibraryFilterChipViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShowChipsAndClickThem {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.menuLibrary)).perform(click())
    }

    @Test
    fun test_1_show_chips() {
        val chips = listOf("Hardcover Fiction", "Hardcover Nonfiction", "Paperback Nonfiction")
        onView(withId(R.id.rvBookCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryFilterChipViewHolder>(
                0,
                scrollTo()
            )
        )
        onView(withId(R.id.rvBookCategory)).check(matches(hasDescendant(withText(chips[0]))))

        onView(withId(R.id.rvBookCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryFilterChipViewHolder>(
                1,
                scrollTo()
            )
        )
        onView(withId(R.id.rvBookCategory)).check(matches(hasDescendant(withText(chips[1]))))


        onView(withId(R.id.rvBookCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryFilterChipViewHolder>(
                2,
                scrollTo()
            )
        )
        onView(withId(R.id.rvBookCategory)).check(matches(hasDescendant(withText(chips[2]))))
    }

    @Test
    fun test_2click_close() {

        onView(withId(R.id.rvBookCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryFilterChipViewHolder>(
                0,
                click()
            )
        )
        onView(withText("DESERT STAR")).check(matches(isDisplayed()))
        onView(withId(R.id.btnFilterChipClose)).perform(click())
        onView(withText("DESERT STAR")).check(matches(isDisplayed()))
        onView(withText("THE BODY KEEPS THE SCORE")).check(matches(isDisplayed()))
        onView(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING")).check(matches(isDisplayed()))
    }

    @Test
    fun test_3_click_first_chip() {
        onView(withId(R.id.rvBookCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryFilterChipViewHolder>(
                0,
                click()
            )
        )
        onView(withText("DESERT STAR")).check(matches(isDisplayed()))
    }

    @Test
    fun test_4_click_second_chip() {
        onView(withId(R.id.rvBookCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryFilterChipViewHolder>(
                1,
                click()
            )
        )
        onView(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING")).check(matches(isDisplayed()))
    }

    @Test
    fun test_5_click_third_chip() {
        onView(withId(R.id.rvBookCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryFilterChipViewHolder>(
                2,
                click()
            )
        )
        onView(withText("THE BODY KEEPS THE SCORE")).check(matches(isDisplayed()))
    }


}