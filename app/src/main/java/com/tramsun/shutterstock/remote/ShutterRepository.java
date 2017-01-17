package com.tramsun.shutterstock.remote;

import android.util.Base64;
import com.tramsun.shutterstock.AppConstants;
import com.tramsun.shutterstock.BuildConfig;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Single;
import timber.log.Timber;

@Singleton public class ShutterRepository {
  private final String authorization;
  private List<ShutterImage> images = new ArrayList<>();

  @Inject ShutterstockApi api;

  private int currentPage = 0;

  @Inject public ShutterRepository() {
    String authString = BuildConfig.SHUTTERSTOCK_CLIENT_ID + ":" + BuildConfig.SHUTTERSTOCK_SECRET;
    authorization = "Basic " + Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);
  }

  public Single<Boolean> fetchFirstImageSet() {
    return fetchPage(1).doOnSubscribe(() -> images.clear());
  }

  public Single<Boolean> fetchNextPage() {
    return fetchPage(currentPage + 1);
  }

  private Single<Boolean> fetchPage(int page) {
    return api.getImages(authorization, page, AppConstants.PER_PAGE_SIZE).map(shutterImages -> {
      // To ignore multiple responses for a single page request
      if (currentPage + 1 == page) {
        currentPage = page;
        images.addAll(shutterImages.getData());
      }
      return true;
    }).onErrorReturn(e -> {
      Timber.e(e);
      return false;
    });
  }

  public List<ShutterImage> getImages() {
    return images;
  }

  public void clear() {
    currentPage = 0;
    images.clear();
  }
}
