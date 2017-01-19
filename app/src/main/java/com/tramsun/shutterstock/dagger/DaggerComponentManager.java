package com.tramsun.shutterstock.dagger;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.tramsun.shutterstock.ShutterApp;
import com.tramsun.shutterstock.dagger.components.ActivityComponent;
import com.tramsun.shutterstock.dagger.components.AppComponent;
import com.tramsun.shutterstock.dagger.components.DaggerActivityComponent;
import com.tramsun.shutterstock.dagger.components.DaggerAppComponent;
import com.tramsun.shutterstock.dagger.module.ActivityModule;
import com.tramsun.shutterstock.dagger.module.AppModule;

public class DaggerComponentManager {
  private static AppComponent component;

  public static void init(ShutterApp app) {
    if (component != null) return;

    component = DaggerAppComponent.builder().appModule(new AppModule(app)).build();

    component.inject(app);
  }

  @NonNull public static AppComponent getComponent() {
    return component;
  }

  @NonNull public static ActivityComponent getActivityComponent(AppCompatActivity activity) {
    return DaggerActivityComponent.builder()
        .appComponent(component)
        .activityModule(new ActivityModule(activity))
        .build();
  }
}
