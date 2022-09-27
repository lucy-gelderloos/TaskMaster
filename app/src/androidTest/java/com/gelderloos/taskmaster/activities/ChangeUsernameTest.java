package com.gelderloos.taskmaster.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.Espresso;
import static androidx.test.espresso.Espresso.pressBack;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gelderloos.taskmaster.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChangeUsernameTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testChangeUsername() {
        onView(withId(R.id.buttonMainActivityToSettings)).perform(click());
        onView(withId(R.id.editTextSettingsEnterUsername)).perform(typeText("Master of Tasks"));
        onView(withId(R.id.buttonSettingsChangeUsername)).perform(click());
        Espresso.closeSoftKeyboard();
        Espresso.pressBack();
        onView(withId(R.id.textViewMainActivityUserTasks)).check(matches(withText("Master of Tasks's Tasks:")));
    }
}
