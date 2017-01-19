package com.tramsun.shutterstock.feature.images;

import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.BaseViewModel;
import com.tramsun.shutterstock.remote.ShutterRepository;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import java.util.List;
import javax.inject.Inject;
import rx.Single;

@ActivityScope public class ImagesViewModel extends BaseViewModel {
  @Inject ShutterRepository repository;

  @Inject ImagesViewModel() {
  }

  public List<ShutterImage> getImages() {
    return repository.getImages();
  }

  Single<Boolean> fetchNextPage() {
    return repository.fetchNextPage();
  }
}
