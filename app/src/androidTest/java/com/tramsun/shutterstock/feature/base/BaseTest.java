package com.tramsun.shutterstock.feature.base;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import com.tramsun.shutterstock.ShutterApp;

public class BaseTest {
  public static Context getContext() {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    return instrumentation.getTargetContext();
  }

  protected static ShutterApp getApplication() {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    return (ShutterApp) instrumentation.getTargetContext().getApplicationContext();
  }
}
