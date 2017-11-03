package com.example.hermes.test;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tw.android.test.activity.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static tw.android.test.activity.login.LoginActivity.FORGET_PASSWORD_ACTION;
import static tw.android.test.activity.login.LoginActivity.LOGIN_ACTION;
import static tw.android.test.activity.login.LoginActivity.REGISTER_ACTION;

/**
 * Created by hermes.hsieh on 2017/11/3.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void clickAllActionButton() {
        onView(withId(R.id.login_action)).perform(click());
        onView(withId(R.id.state_text)).check(matches(withText(LOGIN_ACTION)));

        onView(withId(R.id.register_action)).perform(click());
        onView(withId(R.id.state_text)).check(matches(withText(REGISTER_ACTION)));

        onView(withId(R.id.forget_password_action)).perform(click());
        onView(withId(R.id.state_text)).check(matches(withText(FORGET_PASSWORD_ACTION)));
    }

    @Test
    public void onClickLoginActionButton() {
        onView(withId(R.id.login_action)).perform(click());
        onView(withId(R.id.state_text)).check(matches(withText(LOGIN_ACTION)));
    }
}
