package com.linhtetko.thelibraryapp.library.shelves

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
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
import com.linhtetko.thelibraryapp.views.viewholders.ShelfViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4ClassRunner::class)
class YourShelvesTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.menuLibrary)).perform(click())
        onView(withText(activityTestRule.activity.getString(R.string.lbl_your_shelves))).perform(
            click()
        )

    }


    @Test
    fun your_shelves() {

        val lifecycleOwner = Mockito.mock(LifecycleOwner::class.java)
        val lifecycleRegistry = LifecycleRegistry(lifecycleOwner)
        activityTestRule.runOnUiThread {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        `when`(lifecycleOwner.lifecycle).thenReturn(lifecycleRegistry)
        
        onView(withId(R.id.ivYourShelvesEmpty)).check(matches(isDisplayed()))

        onView(withId(R.id.fabCreateShelf)).perform(click())
        onView(withId(R.id.etCreateSelf)).perform(typeText("Motivation"), closeSoftKeyboard())
        onView(withId(R.id.btnCreateShelf)).perform(click())
        onView(withId(R.id.rvYourShelves)).check(matches(isDisplayed()))
        onView(withId(R.id.rvYourShelves)).check(matches(hasDescendant(withText("Motivation"))))

        onView(withText(activityTestRule.activity.getString(R.string.lbl_your_books))).perform(click())
        onView(first(withId(R.id.ivBookMore))).perform(click())
        onView(withId(R.id.llBookDetailOverviewDownload)).check(matches(isDisplayed()))
        onView(withId(R.id.llBookDetailOverviewDelete)).check(matches(isDisplayed()))
        onView(withId(R.id.llBookDetailOverviewAddToShelves)).check(matches(isDisplayed()))
        onView(withId(R.id.llBookDetailOverviewMarkAsRead)).check(matches(isDisplayed()))
        onView(withId(R.id.llBookDetailOverviewAddToShelves)).perform(click())

        onView(withId(R.id.rvAddToShelves)).check(matches(hasDescendant(withText("Motivation"))))
        onView(withId(R.id.cbAddToShelf)).perform(click())
        onView(withId(R.id.ivAddToShelves)).perform(click())

        onView(withText(activityTestRule.activity.getString(R.string.lbl_your_shelves))).perform(
            click()
        )
        onView(withId(R.id.rvYourShelves)).check(matches(hasDescendant(withText("1 Books"))))

        onView(withId(R.id.rvYourShelves)).perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        onView(withId(R.id.tvShelfDetailName)).check(matches(withText("Motivation")))
        onView(withId(R.id.tvShelfDetailBookCount)).check(matches(withText("1 Books")))
        onView(withId(R.id.rvBookCategory)).check(matches(hasDescendant(withText("Not Started"))))
        onView(withId(R.id.rvBookCategory)).check(matches(hasDescendant(withText("Progress"))))
        onView(withId(R.id.btnShelfDetail)).perform(click())

        onView(withId(R.id.tvShelfTitle)).check(matches(withText("Motivation")))
        onView(withId(R.id.llShelfActionEdit)).check(matches(isDisplayed()))
        onView(withId(R.id.llShelfActionDelete)).check(matches(isDisplayed()))

        onView(withId(R.id.llShelfActionEdit)).perform(click())
        onView(withId(R.id.etCreateSelf)).check(matches(isDisplayed()))
        onView(withId(R.id.etCreateSelf)).perform(
            clearText(),
            typeText("Motivations"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.btnCreateShelf)).perform(click())

        onView(withId(R.id.tvShelfDetailName)).check(matches(withText("Motivations")))
        onView(withId(R.id.btnShelfDetail)).perform(click())
        onView(withId(R.id.llShelfActionDelete)).perform(click())

        onView(withId(R.id.ivYourShelvesEmpty)).check(matches(isDisplayed()))
    }
}