package com.linhtetko.thelibraryapp.home

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.activities.MainActivity
import com.linhtetko.thelibraryapp.utils.first
import com.linhtetko.thelibraryapp.views.viewholders.BookSectionViewHolder
import com.linhtetko.thelibraryapp.views.viewholders.BookViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BookDetailTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun navigate_to_detail_screen_and_verify_detail_screen_components() {

        onView(first(withId(R.id.rvBooks))).perform(RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0, click()))
        onView(first(withId(R.id.vhBook))).perform(click())

        onView(withId(R.id.ivBookDetailImg)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookDetailName)).check(matches(withText("DESERT STAR")))
        onView(withId(R.id.tvBookDetailAuthor)).check(matches(withText("Michael Connelly")))
        onView(withId(R.id.btnBookDetailFreeSample)).check(matches(isDisplayed()))
        onView(withId(R.id.btnBookDetailWishlist)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookDetailWarning)).check(
            matches(
                withText(
                    activityTestRule.activity.getString(
                        R.string.lbl_books_that_you_buy_on_the_google_play_website_can_be_read_in_this_app
                    )
                )
            )
        )
        onView(withId(R.id.tvBookDetailAboutLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookDetailAboutLabel)).check(
            matches(
                withText(
                    activityTestRule.activity.getString(
                        R.string.lbl_about_this_ebook
                    )
                )
            )
        )
        onView(withId(R.id.tvBookDetailAboutBook)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookDetailAboutBook)).check(matches(withText("Ballard and Bosch bury old resentments as they go after two killers.")))
        onView(withId(R.id.tvBookDetailRatingLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookDetailRatingNum)).check(matches(isDisplayed()))
        onView(withId(R.id.rbBookDetailRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookDetailRating)).check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingNum5)).check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingNum4)).check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingNum3)).check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingNum2)).check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingNum1)).check(matches(isDisplayed()))
        onView(withId(R.id.rvReviews)).check(matches(isDisplayed()))

//        onView(withId(R.id.tvBookDetailPublishLabel)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvBookDetailPublished)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvBookDetailPublished)).check(matches(withText("Little, Brown")))

    }
}