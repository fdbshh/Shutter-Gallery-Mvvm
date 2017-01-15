package com.tramsun.shutterstock.feature.base;

import android.os.Bundle;

interface ViewModel<T extends MvvmView> {

  void attach();

  void attach(T view);

  void resume();

  void pause();

  void detach();

  void restoreInstanceState(Bundle savedInstanceState);

  void saveInstanceState(Bundle outState);
}
