package com.tramsun.shutterstock.api;

import com.tramsun.shutterstock.AppConstants;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rx.Single;
import rx.observers.TestSubscriber;

import static com.google.common.truth.Truth.assertThat;

public class RepoTest extends BaseApiTest {

  @Before public void setUp() {
    super.setUp();
  }

  @After public void tearDown() {
    super.tearDown();
  }

  @Test public void verifyInit() throws Exception {
    assertThat(repository).isNotNull();

    assertThat(repository.getImages()).isNotNull();
    assertThat(repository.getImages()).isEmpty();
  }

  @Test public void verifyFetchFirstPageRequest() throws Exception {
    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();

    repository.fetchFirstImageSet().subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    testSubscriber.assertValue(true);
  }

  @Test public void verifyFetchFirstPageResponse() throws Exception {
    verifyFetchFirstPageRequest();

    assertThat(repository.getImages()).isNotEmpty();
    assertThat(repository.getImages()).hasSize(AppConstants.PER_PAGE_SIZE);
  }

  @Test public void verifyFetchMultiPageResponse() throws Exception {
    verifyFetchFirstPageResponse();

    Single<Boolean> call = repository.fetchNextPage();
    int n = new Random(System.currentTimeMillis()).nextInt(5);

    final int[] successCnt = { 0 };
    for (int i = 0; i < n; i++) {
      call = call.flatMap(success -> {
        successCnt[0] += success ? 1 : 0;
        return repository.fetchNextPage();
      });
    }

    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();

    call.subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    Boolean lastCallSuccess = testSubscriber.getOnNextEvents().get(0);
    successCnt[0] += lastCallSuccess ? 1 : 0;

    assertThat(repository.getImages()).isNotEmpty();
    assertThat(repository.getImages()).hasSize(AppConstants.PER_PAGE_SIZE * (successCnt[0] + 1));
  }
}