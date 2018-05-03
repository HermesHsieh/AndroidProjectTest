package com.example.hermes.test;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tw.android.test.activity.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void Error_on_click_login_action_button_show_account_is_empty_if_no_input_account() {
        onView(withId(R.id.login_action)).perform(click());
        onView(withText("請輸入帳號")).check(matches(isDisplayed()));
        onView(withText("確定"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
    }

    @Test
    public void Error_on_click_login_action_button_show_password_is_empty_if_no_input_password() {
        onView(ViewMatchers.withId(R.id.account_input)).perform(ViewActions.typeText("hermes"), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.login_action)).perform(click());
        onView(withText("請輸入密碼")).check(matches(isDisplayed()));
        onView(withText("確定"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
    }

    @Test
    public void Succeed_on_click_login_action_button() {
        onView(ViewMatchers.withId(R.id.account_input)).perform(ViewActions.typeText("hermes"), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.password_input)).perform(ViewActions.typeText("1234567890"), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.login_action)).perform(click());
        onView(withText("登入成功")).check(matches(isDisplayed()));
        onView(withText("確定"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(ViewMatchers.withId(R.id.state_text)).check(matches(withText("hermes")));
    }
}
