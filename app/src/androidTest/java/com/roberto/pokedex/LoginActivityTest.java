package com.roberto.pokedex;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.roberto.pokedex.presentation.Home.HomeActivity;
import com.roberto.pokedex.presentation.Login.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivity = new ActivityTestRule(LoginActivity.class);

    @Before
    public void init() {
        Intents.init();
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
    }

    @After
    public void after(){
        Intents.release();
    }

    @Test
    public void checkEmptyCredentialsError() {
        Activity activity = loginActivity.getActivity();
        String emailError = activity.getResources().getString(R.string.email_error);
        String passwordError = activity.getResources().getString(R.string.password_error);

        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.email)).check(matches(hasErrorText(emailError)));
        onView(withId(R.id.password)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.password)).check(matches(hasErrorText(passwordError)));
    }

    @Test
    public void checkEmptyEmailError() {
        Activity activity = loginActivity.getActivity();
        String emailError = activity.getResources().getString(R.string.email_error);

        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.email)).check(matches(hasErrorText(emailError)));
    }

    @Test
    public void checkEmptyPasswordError() {
        Activity activity = loginActivity.getActivity();
        String passwordError = activity.getResources().getString(R.string.password_error);

        onView(withId(R.id.email)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.password)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.password)).check(matches(hasErrorText(passwordError)));
    }

    @Test
    public void checkCredentialsError() {
        onView(withId(R.id.email)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.credentials_error)).check(matches(isDisplayed()));
    }

    @Test
    public void checkLogInSuccess(){
        onView(withId(R.id.email)).perform(typeText("test@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        intended(hasComponent(HomeActivity.class.getName()));
    }
}