package com.tramsun.shutterstock.dagger.components;

import com.tramsun.shutterstock.ShutterApp;
import com.tramsun.shutterstock.dagger.module.ApiModule;
import com.tramsun.shutterstock.dagger.module.AppModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class, ApiModule.class }) public interface AppComponent
    extends ShareableAppComponent {
  void inject(ShutterApp app);
}