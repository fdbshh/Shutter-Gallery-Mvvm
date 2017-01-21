package com.tramsun.shutterstock.feature.detail;

import com.tramsun.shutterstock.dagger.components.TestActivityComponent;
import com.tramsun.shutterstock.feature.base.BaseViewModelTest;
import com.tramsun.shutterstock.remote.ShutterRepository;
import javax.inject.Inject;
import org.junit.Test;
import rx.observers.TestSubscriber;

import static com.google.common.truth.Truth.assertThat;

public class ImageDetailViewModelTest extends BaseViewModelTest<ImageDetailViewModel> {
  @Inject ShutterRepository repository;

  @Override protected void onComponentCreated(TestActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Test public void verifyViewModel() {
    assertThat(viewModel).isNotNull();

    assertThat(viewModel.getView()).isNull();
  }

  @Test public void verifyViewModelInitialization() {
    verifyViewModel();

    assertThat(viewModel.image).isNull();
  }

  @Test public void verifyImageUrl() {
    verifyViewModel();

    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();

    repository.fetchFirstImageSet().subscribe(testSubscriber);

    testSubscriber.assertCompleted();
    testSubscriber.assertNoErrors();

    if (testSubscriber.getOnNextEvents().get(0)) {
      assertThat(repository.getImages()).isNotNull();
      assertThat(repository.getImages()).isNotEmpty();

      viewModel.setImage(repository.getImages().get(0));

      verifyChanged();

      assertThat(viewModel.getImageUrl()).isEqualTo(
          repository.getImages().get(0).getAssets().getPreview().getUrl());
    }
  }
}
