package com.tramsun.shutterstock.feature.base.navigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

public abstract class Navigator {

  public abstract FragmentActivity getActivity();

  public void startActivity(@NonNull Class<? extends Activity> activityClass) {
    startActivity(activityClass, null);
  }

  public void startActivity(@NonNull Class<? extends Activity> activityClass, Bundle args) {
    Activity activity = getActivity();
    Intent intent = new Intent(activity, activityClass);
    if (args != null) {
      intent.putExtras(args);
    }
    activity.startActivity(intent);
  }

  public void finishActivity() {
    finishActivity(true);
  }

  public void finishActivity(boolean showDefaultAnimation) {
    getActivity().finish();

    if (!showDefaultAnimation) {
      getActivity().overridePendingTransition(0, 0);
    }
  }
}
