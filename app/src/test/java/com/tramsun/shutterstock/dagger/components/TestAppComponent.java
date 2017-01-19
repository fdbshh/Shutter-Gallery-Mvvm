package com.tramsun.shutterstock.dagger.components;

import com.tramsun.shutterstock.BaseTest;
import com.tramsun.shutterstock.dagger.module.ApiModule;
import com.tramsun.shutterstock.dagger.module.TestAppModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { TestAppModule.class, ApiModule.class })
public interface TestAppComponent extends ShareableAppComponent {
  void inject(BaseTest baseTest);
}