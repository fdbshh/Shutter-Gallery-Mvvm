package com.tramsun.shutterstock.feature.base;

import android.app.Instrumentation;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
  @CallSuper @Before public void setUp() {
    Intents.init();
  }

  @CallSuper @After public void tearDown() throws InterruptedException {
    Intents.release();

    // Destroy time
    Thread.sleep(1000);
  }

  protected static Context getContext() {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    return instrumentation.getTargetContext();
  }
}
