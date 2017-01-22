package com.tramsun.shutterstock.feature.launch;

import android.view.View;
import com.tramsun.shutterstock.dagger.components.UnitTestActivityComponent;
import com.tramsun.shutterstock.feature.base.BaseViewModelTest;
import com.tramsun.shutterstock.feature.images.ImagesActivity;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class LaunchViewModelTest extends BaseViewModelTest<LaunchViewModel> {

  @Override protected void onComponentCreated(UnitTestActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Test public void verifyViewModel() {
    assertThat(viewModel).isNotNull();

    assertThat(viewModel.getView()).isNull();

    assertThat(viewModel.repository).isNotNull();
  }

  @Test public void verifyViewModelAttachDetach() {
    verifyViewModel();

    viewModel.attach();

    viewModel.detach();
  }

  @Test public void verifyOnlyAnimationDone() {
    verifyViewModel();

    viewModel.onAnimationDone();

    assertThat(viewModel.animationDone).isTrue();
    assertThat(viewModel.workDone).isFalse();
    assertThat(viewModel.workSuccess).isFalse();

    verifyChanged();

    verify(viewModel.navigator, never()).startActivity(ImagesActivity.class);
    verify(viewModel.navigator, never()).finishActivity();
  }

  @Test public void verifyProgressBarShownAfterAnimationDone() {
    verifyViewModel();

    assertThat(viewModel.getProgressBarVisibility()).isNotEqualTo(View.VISIBLE);

    viewModel.onAnimationDone();

    assertThat(viewModel.getProgressBarVisibility()).isEqualTo(View.VISIBLE);
  }

  @Test public void verifyProgressBarHiddenAfterWorkDone() {
    verifyProgressBarShownAfterAnimationDone();

    viewModel.resume();

    assertThat(viewModel.getProgressBarVisibility()).isNotEqualTo(View.VISIBLE);
  }

  @Test public void verifyOnlyWorkDone() {
    verifyViewModel();

    viewModel.resume();

    verifyChanged();

    assertThat(viewModel.animationDone).isFalse();
    assertThat(viewModel.workDone).isTrue();

    if (viewModel.workSuccess) {
      verify(viewModel.ui, never()).showSnackBar(anyInt());
    } else {
      verify(viewModel.ui).showSnackBar(anyInt());
    }

    verify(viewModel.navigator, never()).startActivity(ImagesActivity.class);
    verify(viewModel.navigator, never()).finishActivity();
  }

  @Test public void verifyAnimationDoneThenWorkDone() {
    verifyOnlyAnimationDone();

    viewModel.resume();

    verifyChanged();

    assertThat(viewModel.animationDone).isTrue();
    assertThat(viewModel.workDone).isTrue();

    if (viewModel.workSuccess) {
      verify(viewModel.ui, never()).showSnackBar(anyInt());
    } else {
      verify(viewModel.ui).showSnackBar(anyInt());
    }

    verify(viewModel.navigator).startActivity(ImagesActivity.class);
    verify(viewModel.navigator).finishActivity();
  }

  @Test public void verifyWorkDoneThenAnimationDone() {
    verifyOnlyWorkDone();

    viewModel.onAnimationDone();

    assertThat(viewModel.animationDone).isTrue();
    assertThat(viewModel.workDone).isTrue();

    if (viewModel.workSuccess) {
      verify(viewModel.ui, never()).showSnackBar(anyInt());
    } else {
      verify(viewModel.ui).showSnackBar(anyInt());
    }

    verifyChanged();

    verify(viewModel.navigator).startActivity(ImagesActivity.class);
    verify(viewModel.navigator).finishActivity();
  }
}