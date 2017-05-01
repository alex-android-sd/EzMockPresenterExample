package com.example.asanchez.myapplication;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {


    boolean mockPresenterInjected = false;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected void afterActivityLaunched() {
            Log.d("MVP","afterActivityLaunched");
            if (!mockPresenterInjected) {
                mockPresenterInjected = true;
                MyApplication application = (MyApplication) getActivity().getApplication();
                try {
                    application.setPresenterProvider(new PresenterProvider() {
                        @Override
                        public MainPresenter getMainPresenter() {
                            return new MainPresenterImpl(new AccountDal() {
                                @Override
                                public boolean isValidLoginId(String userId) {
                                    Log.d("MVP","returning false");
                                    return false;
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getActivity().getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                launchActivity(intent);
            }
        }
    };




    @Test
    public void testIncorrectLoginGoesToRedActivity() {


        onView(withId(R.id.login_field)).perform(click());
        onView(withId(R.id.login_field))
                .perform(typeText("user123"));
        closeSoftKeyboard();
        onView(withId(R.id.log_in_button)).perform(click());
        //verify we are on desired screen

    }

}
