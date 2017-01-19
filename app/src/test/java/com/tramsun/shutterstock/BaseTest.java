package com.tramsun.shutterstock;

import android.support.annotation.CallSuper;
import com.tramsun.shutterstock.dagger.components.DaggerTestAppComponent;
import com.tramsun.shutterstock.dagger.components.TestAppComponent;
import com.tramsun.shutterstock.remote.ShutterRepository;
import javax.inject.Inject;

public class BaseTest {
  private TestAppComponent testComponent;

  @Inject ShutterRepository repository;

  BaseTest() {
    testComponent = DaggerTestAppComponent.builder().build();
    testComponent.inject(this);
  }

  @CallSuper public void setUp() {
  }

  @CallSuper public void tearDown() {
    repository.clear();
  }
}
