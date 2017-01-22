package com.tramsun.shutterstock.dagger.components;

import com.tramsun.shutterstock.api.BaseApiTest;
import com.tramsun.shutterstock.dagger.module.ApiModule;
import com.tramsun.shutterstock.dagger.module.TestAppModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { TestAppModule.class, ApiModule.class })
public interface UnitTestAppComponent extends ShareableAppComponent {
  void inject(BaseApiTest baseApiTest);
}