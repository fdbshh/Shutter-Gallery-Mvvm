package com.tramsun.shutterstock.api;

import android.support.annotation.CallSuper;
import com.tramsun.shutterstock.dagger.components.DaggerTestAppComponent;
import com.tramsun.shutterstock.remote.ShutterRepository;
import javax.inject.Inject;

public class BaseApiTest {

  @Inject ShutterRepository repository;

  BaseApiTest() {
    DaggerTestAppComponent.builder().build().inject(this);
  }

  @CallSuper public void setUp() {
  }

  @CallSuper public void tearDown() {
    repository.clear();
  }
}
