package com.tramsun.shutterstock;

import android.app.Application;
import com.tramsun.shutterstock.dagger.DaggerComponentManager;
import timber.log.Timber;

public class ShutterstockApp extends Application {
  @Override public void onCreate() {
    super.onCreate();

    DaggerComponentManager.init(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
