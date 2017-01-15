package com.tramsun.shutterstock.feature.base.navigator;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class ActivityNavigator extends Navigator {

  private final FragmentActivity activity;

  public ActivityNavigator(FragmentActivity activity) {
    this.activity = activity;
  }

  @Override public final FragmentActivity getActivity() {
    return activity;
  }

  @Override final FragmentManager getChildFragmentManager() {
    throw new UnsupportedOperationException("Activities do not have a child fragment manager.");
  }
}
