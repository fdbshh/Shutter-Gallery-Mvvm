package com.tramsun.shutterstock;

import android.app.Application;
import com.tramsun.shutterstock.dagger.DaggerComponentManager;
import timber.log.Timber;

public class ShutterApp extends Application {
  @Override public void onCreate() {
    super.onCreate();

    initDagger();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  protected void initDagger() {
    DaggerComponentManager.init(this);
  }
}
