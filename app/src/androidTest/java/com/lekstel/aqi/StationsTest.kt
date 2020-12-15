package com.lekstel.aqi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.lekstel.aqi.main.presentation.view.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StationsTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @Test
    fun testDialog() {
        Thread.sleep(1000)
        onView(withId(R.id.filters_item)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.filters_root))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testItemClick() {
        Thread.sleep(1000)
        onView(withId(R.id.rv_stations)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                clickItem(R.id.item_root)
            )
        )
        Thread.sleep(1000)
        onView(withId(R.id.details_root))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testRadiusFilters() {
        Thread.sleep(1000)
        onView(withId(R.id.filters_item)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.radius_radio_group))
            .check(matches(hasMinimumChildCount(2)))
    }

    @Test
    fun testQualityFilters() {
        Thread.sleep(1000)
        onView(withId(R.id.filters_item)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.aqi_radio_group))
            .check(matches(hasMinimumChildCount(2)))
    }

    @Test
    fun testFilterClick() {
        Thread.sleep(1000)
        onView(withId(R.id.filters_item)).perform(click())
        Thread.sleep(1000)
        onView(allOf(withText("20"), withParent(withId(R.id.radius_radio_group)))).perform(click())
        Thread.sleep(1000)
        onView(allOf(withText("50"), withParent(withId(R.id.radius_radio_group)))).perform(click())
            .check(matches(isChecked()))
        Thread.sleep(1000)
        onView(allOf(withText("20"), withParent(withId(R.id.radius_radio_group))))
            .check(matches(isNotChecked()))
    }

    private fun clickItem(id: Int) = object : ViewAction {
        override fun getConstraints() = null
        override fun getDescription() = "click on item"
        override fun perform(uiController: UiController, view: View) {
            view.findViewById<View>(id).performClick()
        }
    }
}