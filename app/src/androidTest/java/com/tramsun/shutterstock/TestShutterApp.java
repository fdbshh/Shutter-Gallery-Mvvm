package com.tramsun.shutterstock;

import com.tramsun.shutterstock.dagger.DaggerComponentManager;
import com.tramsun.shutterstock.dagger.module.TestApiModule;

public class TestShutterApp extends ShutterApp {
  @Override protected void initDagger() {
    DaggerComponentManager.init(this, new TestApiModule());
  }
}
