package com.tramsun.shutterstock.feature.base;

import android.databinding.Observable;
import android.support.annotation.CallSuper;
import com.tramsun.shutterstock.dagger.components.DaggerUnitTestActivityComponent;
import com.tramsun.shutterstock.dagger.components.DaggerUnitTestAppComponent;
import com.tramsun.shutterstock.dagger.components.UnitTestActivityComponent;
import com.tramsun.shutterstock.dagger.components.UnitTestAppComponent;
import com.tramsun.shutterstock.utils.RxTestUtils;
import javax.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class BaseViewModelTest<T extends BaseViewModel> {
  @Inject protected T viewModel;

  private Observable.OnPropertyChangedCallback onPropertyChangedCallback;

  @Before @CallSuper public void setUp() {
    UnitTestAppComponent appComponent = DaggerUnitTestAppComponent.builder().build();
    UnitTestActivityComponent activityComponent =
        DaggerUnitTestActivityComponent.builder().testAppComponent(appComponent).build();

    onComponentCreated(activityComponent);

    onPropertyChangedCallback = mock(Observable.OnPropertyChangedCallback.class);
    viewModel.addOnPropertyChangedCallback(onPropertyChangedCallback);
  }

  @After @CallSuper public void tearDown() {
    viewModel.removeOnPropertyChangedCallback(onPropertyChangedCallback);
  }

  protected final void verifyChanged() {
    verify(onPropertyChangedCallback, atLeastOnce()).onPropertyChanged(any(Observable.class),
        eq(0));
  }

  @BeforeClass public static void setupOnce() {
    RxTestUtils.initTestSchedulers();
  }

  protected abstract void onComponentCreated(UnitTestActivityComponent activityComponent);
}
