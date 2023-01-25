package com.linhtetko.thelibraryapp.home

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.UiThreadTestRule
import com.linhtetko.thelibraryapp.R
import com.linhtetko.thelibraryapp.activities.MainActivity
import com.linhtetko.thelibraryapp.adapters.BookSectionAdapter
import com.linhtetko.thelibraryapp.data.vos.BookVO
import com.linhtetko.thelibraryapp.data.vos.SectionBookVO
import com.linhtetko.thelibraryapp.delegates.BookDelegate
import com.linhtetko.thelibraryapp.delegates.BookSectionDelegate
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.matches
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4ClassRunner::class)
class SectionAndBookDisplayTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    public val uiThreadTestRule: UiThreadTestRule = UiThreadTestRule()
    private lateinit var sections: List<SectionBookVO>

    @Before
    fun setUp() {
        sections = listOf(
            SectionBookVO(
                books = listOf(
                    BookVO(
                        author = "Aung Aung",
                        publisher = "PADC",
                        description = "This is Flutter Book",
                        title = "Flutter For All",
                    ),
                    BookVO(
                        author = "Maung Maung",
                        publisher = "PADC",
                        description = "This is Android Book",
                        title = "Android For All",
                    ),
                    BookVO(
                        author = "Mya Mya",
                        publisher = "PADC",
                        description = "This is Kotlin Book",
                        title = "Kotlin For All",
                    ),

                    ),
                listName = "Books For You"
            )
        )
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun is_correct_section_name_and_book() {



        onView(withId(R.id.rvBookSections)).check { view, noViewFoundException ->

            val sectionAdapter = BookSectionAdapter(
                bookDelegate = mock(BookDelegate::class.java),
                bookSectionDelegate = mock(BookSectionDelegate::class.java),
                sections = sections
            )

            activityTestRule.activity.runOnUiThread {
                val recyclerView = view as RecyclerView
                recyclerView.adapter = sectionAdapter
            }

        }

        onView(withText("Books For You")).check(matches(isDisplayed()))
        onView(withText("Flutter For All")).check(matches(isDisplayed()))
        onView(withText("Android For All")).check(matches(isDisplayed()))
        onView(withText("Kotlin For All")).check(matches(isDisplayed()))

    }


}