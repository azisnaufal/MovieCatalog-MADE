package com.oazisn.moviecatalog.mvvm.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.android.material.tabs.TabLayout
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.utils.EspressoIdlingResource
import com.oazisn.moviecatalog.extension.NestedScrollViewExtension
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    companion object {
        const val POSITION = 10
    }

    @Before
    fun before() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun after() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    private fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = allOf(
                isDisplayed(),
                isAssignableFrom(TabLayout::class.java)
            )

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }

    fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    @Test
    fun checkTabLayout() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.swipeRefresh))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                POSITION
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.swipeRefresh))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                POSITION
            )
        )
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(isRoot()).perform(waitFor(500))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.swipeRefresh))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        onView(withId(R.id.iv_poster)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtitle)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_director)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_writer)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_stars)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_creator)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))

    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(isRoot()).perform(waitFor(500))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.swipeRefresh))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        onView(withId(R.id.iv_poster)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtitle)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_director)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_writer)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_stars)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_creator)).perform(NestedScrollViewExtension())
            .check(matches(isDisplayed()))

    }

    @Test
    fun favoriteMovie() {
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(isRoot()).perform(waitFor(1000))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        val backButtonOnToolbar = onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(Matchers.`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        backButtonOnToolbar.perform(click())
        val favIconOnMenuToolbar = onView(
            Matchers.allOf(
                withId(R.id.favorite), withContentDescription("Favorite"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        favIconOnMenuToolbar.perform(click())
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }

    @Test
    fun favoriteTvShow() {
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(isRoot()).perform(waitFor(1000))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        val backButtonOnToolbar = onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(Matchers.`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        backButtonOnToolbar.perform(click())
        val favIconOnMenuToolbar = onView(
            Matchers.allOf(
                withId(R.id.favorite), withContentDescription("Favorite"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        favIconOnMenuToolbar.perform(click())
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }
}