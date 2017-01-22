package com.tramsun.shutterstock.utils;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

public class CustomMatchers {
  public static Matcher<View> withAlpha(final Matcher<Float> floatMatcher) {
    checkNotNull(floatMatcher);
    return new BoundedMatcher<View, TextView>(TextView.class) {
      String preDescription;

      @Override protected boolean matchesSafely(TextView view) {
        preDescription = "alpha=" + view.getAlpha();
        System.err.println(preDescription);
        return floatMatcher.matches(view.getAlpha());
      }

      @Override public void describeTo(Description description) {
        description.appendText(preDescription);
        description.appendText(". Alpha expected: ");
        floatMatcher.describeTo(description);
      }
    };
  }

  public static Matcher<View> withScale(final Matcher<Float> floatMatcher) {
    checkNotNull(floatMatcher);
    return new BoundedMatcher<View, ImageView>(ImageView.class) {
      String preDescription;

      @Override protected boolean matchesSafely(ImageView view) {
        preDescription = "scaleX=" + view.getScaleX() + ", scaleY=" + view.getScaleY();
        System.err.println(preDescription);
        return floatMatcher.matches(view.getScaleX()) && floatMatcher.matches(view.getScaleY());
      }

      @Override public void describeTo(Description description) {
        description.appendText(preDescription);
        description.appendText(". Scale expected: ");
        floatMatcher.describeTo(description);
      }
    };
  }

  public static Matcher<View> withCount(final Matcher<Integer> integerMatcher) {
    checkNotNull(integerMatcher);
    return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

      @Override protected boolean matchesSafely(RecyclerView view) {
        System.err.println("recyclerView count=" + view.getChildCount());
        return integerMatcher.matches(view.getAdapter().getItemCount());
      }

      @Override public void describeTo(Description description) {
        description.appendText(". Count expected: ");
        integerMatcher.describeTo(description);
      }
    };
  }
}
