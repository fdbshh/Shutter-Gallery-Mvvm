package com.tramsun.shutterstock.dagger.module;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.UiModule;
import com.tramsun.shutterstock.feature.base.navigator.Navigator;
import dagger.Module;
import dagger.Provides;
import org.mockito.Mockito;

@Module public class TestActivityModule {

  private final AppCompatActivity activity;

  public TestActivityModule() {
    this.activity = Mockito.mock(AppCompatActivity.class);
  }

  @Provides Activity provideActivity() {
    return activity;
  }

  @Provides @ActivityScope Navigator provideNavigator() {
    return Mockito.mock(Navigator.class);
  }

  @Provides @ActivityScope RxPermissions provideRxPermissions() {
    return Mockito.mock(RxPermissions.class);
  }

  @Provides @ActivityScope UiModule provideUiModule() {
    return Mockito.mock(UiModule.class);
  }
}
