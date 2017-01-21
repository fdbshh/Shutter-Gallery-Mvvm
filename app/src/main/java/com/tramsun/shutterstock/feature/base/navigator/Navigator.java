package com.tramsun.shutterstock.feature.base.navigator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class Navigator {

  public abstract FragmentActivity getActivity();

  abstract FragmentManager getChildFragmentManager();

  public final void startActivity(Intent intent) {
    if (intent != null) {
      getActivity().startActivity(intent);
    }
  }

  public final void startActivityAndFinish(Intent intent) {
    if (intent != null) {
      getActivity().startActivity(intent);
      getActivity().finish();
    }
  }

  public final void startActivity(@NonNull String action) {
    getActivity().startActivity(new Intent(action));
  }

  public final void startActivity(@NonNull String action, @NonNull Uri uri) {
    getActivity().startActivity(new Intent(action, uri));
  }

  public void startActivity(@NonNull Class<? extends Activity> activityClass) {
    startActivity(activityClass, null);
  }

  public final void startActivityWithAnimation(@NonNull Class<? extends Activity> activityClass,
      int inAnimation, int outAnimation) {
    startActivityWithAnimation(activityClass, inAnimation, outAnimation, null);
  }

  public final void startActivityWithAnimation(@NonNull Intent intent, int inAnimation,
      int outAnimation) {
    startActivity(intent);
    getActivity().overridePendingTransition(inAnimation, outAnimation);
  }

  public final void startActivityWithAnimation(@NonNull Class<? extends Activity> activityClass,
      int inAnimation, int outAnimation, Bundle args) {
    Activity activity = getActivity();
    Intent intent = new Intent(activity, activityClass);
    if (args != null) {
      intent.putExtras(args);
    }
    activity.startActivity(intent);
    getActivity().overridePendingTransition(inAnimation, outAnimation);
  }

  public final void startActivityWithAnimation(@NonNull Intent intent, int inAnimation,
      int outAnimation, int flags) {
    Activity activity = getActivity();
    intent.setFlags(flags);
    activity.startActivity(intent);
    getActivity().overridePendingTransition(inAnimation, outAnimation);
  }

  public final void startActivityWithAnimation(@NonNull Class<? extends Activity> activityClass,
      int inAnimation, int outAnimation, int flags) {
    Activity activity = getActivity();
    Intent intent = new Intent(activity, activityClass);
    intent.setFlags(flags);
    activity.startActivity(intent);
    getActivity().overridePendingTransition(inAnimation, outAnimation);
  }

  public void startActivity(@NonNull Class<? extends Activity> activityClass, Bundle args) {
    Activity activity = getActivity();
    Intent intent = new Intent(activity, activityClass);
    if (args != null) {
      intent.putExtras(args);
    }
    activity.startActivity(intent);
  }

  public final void replaceFragment(@IdRes int containerId, Fragment fragment, Bundle args) {
    replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment, null,
        args, false, null);
  }

  public void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment,
      @NonNull String fragmentTag, Bundle args) {
    replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment,
        fragmentTag, args, false, null);
  }

  public final void replaceFragmentAndAddToBackStack(@IdRes int containerId, Fragment fragment,
      Bundle args, String backstackTag) {
    replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment, null,
        args, true, backstackTag);
  }

  public void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
      @NonNull String fragmentTag, Bundle args, String backstackTag) {
    replaceFragmentInternal(getActivity().getSupportFragmentManager(), containerId, fragment,
        fragmentTag, args, true, backstackTag);
  }

  public final void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment,
      Bundle args) {
    replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, null, args, false,
        null);
  }

  public void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment,
      @NonNull String fragmentTag, Bundle args) {
    replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, fragmentTag, args,
        false, null);
  }

  public final void replaceChildFragmentAndAddToBackStack(@IdRes int containerId,
      @NonNull Fragment fragment, Bundle args, String backstackTag) {
    replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, null, args, true,
        backstackTag);
  }

  public final void replaceChildFragmentAndAddToBackStack(@IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag, Bundle args, String backstackTag) {
    replaceFragmentInternal(getChildFragmentManager(), containerId, fragment, fragmentTag, args,
        true, backstackTag);
  }

  @SuppressLint("CommitTransaction")
  private void replaceFragmentInternal(FragmentManager fm, @IdRes int containerId,
      Fragment fragment, String fragmentTag, Bundle args, boolean addToBackstack,
      String backstackTag) {
    if (args != null) {
      fragment.setArguments(args);
    }
    FragmentTransaction ft = fm.beginTransaction().replace(containerId, fragment, fragmentTag);
    if (addToBackstack) {
      ft.addToBackStack(backstackTag).commitNow();
    } else {
      ft.commit();
      fm.executePendingTransactions();
    }
  }

  public void finishActivity() {
    finishActivity(true);
  }

  public void finishActivityWithAnimation(int inAnimation, int outAnimation) {
    getActivity().finish();
    getActivity().overridePendingTransition(inAnimation, outAnimation);
  }

  public void finishActivityWithAnimation(int inAnimation, int outAnimation, int result) {
    getActivity().setResult(result);
    getActivity().finish();
    getActivity().overridePendingTransition(inAnimation, outAnimation);
  }

  public void showDialog(DialogFragment dialog, String tag) {
    dialog.show(getActivity().getSupportFragmentManager(), tag);
  }

  public void showDialog(DialogFragment dialog) {
    showDialog(dialog, null);
  }

  public void finishActivity(boolean showDefaultAnimation) {
    getActivity().finish();

    if (!showDefaultAnimation) {
      getActivity().overridePendingTransition(0, 0);
    }
  }
}
