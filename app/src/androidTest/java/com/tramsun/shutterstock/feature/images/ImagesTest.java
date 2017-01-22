package com.tramsun.shutterstock.feature.images;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.DaggerComponentManager;
import com.tramsun.shutterstock.feature.base.BaseTest;
import com.tramsun.shutterstock.feature.detail.ImageDetailActivity;
import com.tramsun.shutterstock.remote.ShutterRepository;
import okhttp3.Dispatcher;
import org.junit.Rule;
import org.junit.Test;
import rx.observers.TestSubscriber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.tramsun.shutterstock.AppConstants.PER_PAGE_SIZE;
import static com.tramsun.shutterstock.utils.CustomMatchers.withCount;
import static org.hamcrest.CoreMatchers.equalTo;

public class ImagesTest extends BaseTest {

  @Rule public ActivityTestRule<ImagesActivity> activityTestRule =
      new ActivityTestRule<>(ImagesActivity.class, false, false);

  @Override public void setUp() {
    super.setUp();

    ShutterRepository repo = DaggerComponentManager.getComponent().provideShutterRepository();

    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
    repo.fetchFirstImageSet().subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();

    activityTestRule.launchActivity(null);
  }

  @Test public void verifyListVisible() {

    onView(withId(R.id.images_list)).check(matches(isDisplayed()));
  }

  @Test public void verifyFirstRunHasImages() {

    onView(withId(R.id.images_list)).check(matches(withCount(equalTo(PER_PAGE_SIZE))));
  }

  @Test public void verifyScrollToLastItemFetchesNextPage() {

    onView(withId(R.id.images_list)).perform(scrollToPosition(PER_PAGE_SIZE - 1));

    onView(withId(R.id.images_list)).check(matches(withCount(equalTo(PER_PAGE_SIZE * 2))));
  }

  @Test public void verifyScrollToLastItem10Times() {

    int n = 10;

    for (int i = 1; i <= n; i++) {

      onView(withId(R.id.images_list)).perform(scrollToPosition(i * PER_PAGE_SIZE - 1));

      onView(withId(R.id.images_list)).check(matches(withCount(equalTo(PER_PAGE_SIZE * (i + 1)))));
    }
  }

  @Test public void verifyImageDetailLaunched() throws InterruptedException {

    onView(withId(R.id.images_list)).perform(
        RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

    Dispatcher dispatcher =
        DaggerComponentManager.getComponent().provideOkHttpClient().dispatcher();

    for (int i = 0; i < 20 && dispatcher.runningCallsCount() > 0; i++) {
      Thread.sleep(500);
    }

    intended(hasComponent(ImageDetailActivity.class.getName()));
  }
}
