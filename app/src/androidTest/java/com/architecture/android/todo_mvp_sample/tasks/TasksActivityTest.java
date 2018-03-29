package com.architecture.android.todo_mvp_sample.tasks;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.architecture.android.todo_mvp_sample.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by yangsimin on 2018/3/29.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TasksActivityTest {
    @Rule
    public ActivityTestRule<TasksActivity> mStatisticsActivityTestRule =
            new ActivityTestRule<>(TasksActivity.class, true, false);


    @Before
    public void intentStartActivity() {

        // Lazily start the Activity from the ActivityTestRule
        Intent startIntent = new Intent();
        mStatisticsActivityTestRule.launchActivity(startIntent);
    }

    @Test
    public void Tasks_ShowsNonEmptyMessage() throws Exception {
        // Check that the active and completed tasks text is displayed
        onView(withId(R.id.add_item)).check(matches(isDisplayed()));
        onView(withId(R.id.add_item)).perform(click());
        onView(withId(R.id.item_delete)).check(matches(isDisplayed()));
    }
}