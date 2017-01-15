package com.tramsun.shutterstock.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tramsun.shutterstock.dagger.qualifier.ActivityContext;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.navigator.ActivityNavigator;
import com.tramsun.shutterstock.feature.base.navigator.Navigator;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {

  private final AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Provides @ActivityContext Context provideActivityContext() {
    return activity;
  }

  @Provides Activity provideActivity() {
    return activity;
  }

  @Provides @ActivityScope FragmentManager provideFragmentManager() {
    return activity.getSupportFragmentManager();
  }

  @Provides @ActivityScope Navigator provideNavigator() {
    return new ActivityNavigator(activity);
  }

  @Provides @ActivityScope RxPermissions provideRxPermissions() {
    return new RxPermissions(activity);
  }
}
