package com.tramsun.shutterstock.dagger.components;

import com.tramsun.shutterstock.dagger.module.ActivityModule;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.launch.LaunchActivity;
import dagger.Component;

@ActivityScope @Component(dependencies = AppComponent.class, modules = { ActivityModule.class })
public interface ActivityComponent {
  void inject(LaunchActivity launchActivity);
}
