package com.tramsun.shutterstock.feature.base;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import rx.subscriptions.CompositeSubscription;

public class BaseViewModel<T extends MvvmView> extends BaseObservable implements ViewModel<T> {

  private T view;

  public T getView() {
    return view;
  }

  protected CompositeSubscription subscriptions = new CompositeSubscription();

  // Never override both attach() and attach(T view)
  @Override public void attach() {
    // Override if required
  }

  @CallSuper @Override public void attach(T view) {
    this.view = view;
  }

  @Override public void resume() {
    // Override if required
  }

  @Override public void pause() {
    // Override if required
  }

  @CallSuper @Override public void detach() {
    subscriptions.unsubscribe();
  }

  @Override public void restoreInstanceState(Bundle savedInstanceState) {
    // Override if required
  }

  @Override public void saveInstanceState(Bundle outState) {
    // Override if required
  }
}
