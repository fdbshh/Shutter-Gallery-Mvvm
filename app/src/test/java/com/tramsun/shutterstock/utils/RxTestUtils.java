package com.tramsun.shutterstock.utils;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

public class RxTestUtils {

  public static void initTestSchedulers() {
    RxAndroidPlugins.getInstance().reset();
    RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
      @Override public Scheduler getMainThreadScheduler() {
        return Schedulers.immediate();
      }
    });

    RxJavaHooks.reset();
    RxJavaHooks.setOnIOScheduler(scheduler -> Schedulers.immediate());
  }
}
