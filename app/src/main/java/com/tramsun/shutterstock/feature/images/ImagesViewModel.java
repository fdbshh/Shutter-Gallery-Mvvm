package com.tramsun.shutterstock.feature.images;

import android.support.annotation.Nullable;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.BaseViewModel;
import com.tramsun.shutterstock.remote.ShutterRepository;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import rx.Single;

@ActivityScope public class ImagesViewModel extends BaseViewModel {
  @Inject ShutterRepository repository;
  private AtomicBoolean fetchInProgress = new AtomicBoolean(false);

  @Inject ImagesViewModel() {
  }

  public List<ShutterImage> getImages() {
    return repository.getImages();
  }

  @Nullable Single<Boolean> fetchNextPage() {
    if (fetchInProgress.getAndSet(true)) {
      return null;
    }

    return repository.fetchNextPage().doOnSuccess(success -> fetchInProgress.set(false));
  }
}
