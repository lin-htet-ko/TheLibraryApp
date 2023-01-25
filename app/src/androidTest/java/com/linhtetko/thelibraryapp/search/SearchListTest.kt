package com.linhtetko.thelibraryapp.search

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
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
import com.linhtetko.thelibraryapp.adapters.SearchBooksAdapter
import com.linhtetko.thelibraryapp.views.viewholders.SearchBookViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchListTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun search_word_and_get_list() {
        onView(withId(R.id.vpSearchbar)).perform(click())
        onView(withId(R.id.etSearch)).perform(typeText("apple"), closeSoftKeyboard())
        onView(withId(R.id.rvSearchBooks)).check(matches(isDisplayed()))

        val lifecycleOwner = mock(LifecycleOwner::class.java)
        val lifecycleRegistry = LifecycleRegistry(lifecycleOwner)
        activityTestRule.runOnUiThread {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
        `when`(lifecycleOwner.lifecycle).thenReturn(lifecycleRegistry)

        onView(withId(R.id.rvSearchBooks)).check { view, _ ->
            view as RecyclerView
            val adapter = view.adapter as SearchBooksAdapter

            assert(adapter.itemCount == 10)

        }

        onView(withId(R.id.rvSearchBooks)).check(matches(isDisplayed()))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Apple Pest Management for Home Gardeners"))))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Apple Pie"))))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("The Apple Grower"))))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Studies in Midwest Apple Marketing"))))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Apple Rootstocks for Intensive Orchards"))))
        onView(withId(R.id.rvSearchBooks)).perform(RecyclerViewActions.actionOnItemAtPosition<SearchBookViewHolder>(5, scrollTo()))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Apple Storage"))))
        onView(withId(R.id.rvSearchBooks)).perform(RecyclerViewActions.actionOnItemAtPosition<SearchBookViewHolder>(9, scrollTo()))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Exploring Apple GS/OS and ProDOS 8"))))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("At the Apple's Core"))))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Apple Delights Cookbook"))))
        onView(withId(R.id.rvSearchBooks)).check(matches(hasDescendant(withText("Holstein-Friesian Herd Book"))))
    }
}