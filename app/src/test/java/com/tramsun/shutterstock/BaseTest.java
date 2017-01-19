package com.tramsun.shutterstock;

import android.support.annotation.CallSuper;
import com.tramsun.shutterstock.dagger.components.DaggerTestAppComponent;
import com.tramsun.shutterstock.dagger.components.TestAppComponent;
import com.tramsun.shutterstock.remote.ShutterRepository;
import javax.inject.Inject;
import org.junit.Before;

public class BaseTest {
  private TestAppComponent testComponent;

  @Inject ShutterRepository repository;

  BaseTest() {
    testComponent = DaggerTestAppComponent.builder().build();
    testComponent.inject(this);
  }

  @CallSuper @Before public void setup() {
    repository.clear();
  }
}
