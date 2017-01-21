package com.tramsun.shutterstock.feature.images;

import com.tramsun.shutterstock.AppConstants;
import com.tramsun.shutterstock.dagger.components.TestActivityComponent;
import com.tramsun.shutterstock.feature.base.BaseViewModelTest;
import java.util.Random;
import org.junit.Test;
import rx.Single;
import rx.observers.TestSubscriber;

import static com.google.common.truth.Truth.assertThat;

public class ImagesViewModelTest extends BaseViewModelTest<ImagesViewModel> {

  @Override protected void onComponentCreated(TestActivityComponent activityComponent) {
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

  @Test public void verifyInitialStateEmpty() {
    verifyViewModel();

    assertThat(viewModel.getImages()).isNotNull();
    assertThat(viewModel.getImages()).isEmpty();
  }

  @Test public void verifyImagesCountAfterNCalls() {
    Single<Boolean> call = viewModel.fetchNextPage();

    assertThat(call).isNotNull();

    int n = new Random(System.currentTimeMillis()).nextInt(5);

    final int[] successCnt = { 0 };
    for (int i = 0; i < n; i++) {
      call = call.flatMap(success -> {
        successCnt[0] += success ? 1 : 0;
        return viewModel.fetchNextPage();
      });
    }

    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();

    call.subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    Boolean lastCallSuccess = testSubscriber.getOnNextEvents().get(0);
    successCnt[0] += lastCallSuccess ? 1 : 0;

    assertThat(viewModel.getImages()).isNotEmpty();
    assertThat(viewModel.getImages()).hasSize(AppConstants.PER_PAGE_SIZE * (successCnt[0]));
  }

  @Test public void verifyOnlyOneApiCallSimultaneously() {
    verifyViewModel();

    assertThat(viewModel.getImages()).isNotNull();
    assertThat(viewModel.getImages()).isEmpty();

    Single<Boolean> call = viewModel.fetchNextPage();
    assertThat(call).isNotNull();

    assertThat(viewModel.fetchNextPage()).isNull();

    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
    call.subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    testSubscriber.assertValue(true);
  }
}