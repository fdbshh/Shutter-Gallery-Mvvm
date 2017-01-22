package com.tramsun.shutterstock.feature.launch;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.feature.base.BaseTest;
import com.tramsun.shutterstock.feature.images.ImagesActivity;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.tramsun.shutterstock.utils.CustomMatchers.withAlpha;
import static com.tramsun.shutterstock.utils.CustomMatchers.withScale;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

public class LaunchTest extends BaseTest {

  @Rule public ActivityTestRule<LaunchActivity> activityTestRule =
      new ActivityTestRule<>(LaunchActivity.class);

  @Test public void verifyInitialState() throws Exception {

    onView(withId(R.id.activity_launch)).check(matches(isDisplayed()));

    onView(withId(R.id.logo_container)).check(matches(isDisplayed()));

    onView(withId(R.id.app_name)).check(matches(isDisplayed()));

    onView(withId(R.id.app_name)).check(matches(withAlpha(equalTo(0f))));

    onView(withId(R.id.progress_layout)).check(matches(not(isDisplayed())));
  }

  @Test public void verifyLogoScale() throws Exception {

    Thread.sleep(LaunchActivity.ANIMATION_DURATION);

    onView(withId(R.id.logo_container)).check(matches(withScale(equalTo(2f))));
  }

  @Test public void verifyTitleAlpha() throws Exception {

    Thread.sleep(LaunchActivity.ANIMATION_DURATION);

    onView(withId(R.id.logo_container)).check(matches(withScale(equalTo(2f))));

    Thread.sleep(LaunchActivity.ANIMATION_DURATION / 2);

    onView(withId(R.id.app_name)).check(matches(withAlpha(Matchers.greaterThan(0f))));
  }

  @Test public void verifyImagesScreenLaunch() throws Exception {

    Thread.sleep(LaunchActivity.ANIMATION_DURATION * 3);

    intended(hasComponent(ImagesActivity.class.getName()));
  }

  @Test public void verifyImagesScreenNotLaunchIfQuitBeforeCompletion() throws Exception {

    Thread.sleep(LaunchActivity.ANIMATION_DURATION);

    activityTestRule.getActivity().finish();

    Thread.sleep(LaunchActivity.ANIMATION_DURATION * 2);

    Intents.times(0);
  }
}
