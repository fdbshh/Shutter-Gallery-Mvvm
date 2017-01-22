package com.tramsun.shutterstock.feature.detail;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.feature.base.BaseTest;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.tramsun.shutterstock.dagger.module.TestApiModule.getSampleShutterImage;

public class ImageDetailTest extends BaseTest {

  @Override public void setUp() {
    super.setUp();

    Intent intent = ImageDetailActivity.getImageIntent(getContext(), getSampleShutterImage());
    activityTestRule.launchActivity(intent);
  }

  @Rule public ActivityTestRule<ImageDetailActivity> activityTestRule =
      new ActivityTestRule<>(ImageDetailActivity.class, false, false);

  @Test public void verifyInitialization() {
    onView(withId(R.id.image_detail)).check(matches(isDisplayed()));
  }
}
