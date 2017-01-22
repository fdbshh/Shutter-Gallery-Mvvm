package com.tramsun.shutterstock.dagger.module;

import com.tramsun.shutterstock.AppConstants;
import com.tramsun.shutterstock.remote.ShutterApi;
import com.tramsun.shutterstock.remote.models.Assets;
import com.tramsun.shutterstock.remote.models.LargeThumb;
import com.tramsun.shutterstock.remote.models.Preview;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import com.tramsun.shutterstock.remote.models.ShutterImages;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;
import rx.Single;

public class TestApiModule extends ApiModule {

  @Override ShutterApi provideShutterApi(Retrofit retrofit) {
    return (authorization, page, perPage) -> Single.just(getSampleShutterImages());
  }

  private ShutterImages getSampleShutterImages() {
    List<ShutterImage> data = new ArrayList<>();
    for (int i = 0; i < AppConstants.PER_PAGE_SIZE; i++) {
      data.add(getSampleShutterImage());
    }

    ShutterImages shutterImages = new ShutterImages();
    shutterImages.setPage(1);
    shutterImages.setPerPage(AppConstants.PER_PAGE_SIZE);
    shutterImages.setSearchId("BXpqCi8DDD4rB4qgwLRXrw");
    shutterImages.setData(data);
    return shutterImages;
  }

  public static ShutterImage getSampleShutterImage() {
    ShutterImage shutterImage = new ShutterImage();
    Assets assets = new Assets();

    LargeThumb largeThumb = new LargeThumb();
    largeThumb.setUrl(
        "https://thumb9.shutterstock.com/thumb_large/91858/519218686/stock-photo-merry-christmas-and-happy-holidays-cute-little-child-girl-with-xmas-present-santa-claus-flying-in-519218686.jpg");
    assets.setLargeThumb(largeThumb);

    Preview preview = new Preview();
    preview.setUrl(
        "https://image.shutterstock.com/display_pic_with_logo/91858/519218686/stock-photo-merry-christmas-and-happy-holidays-cute-little-child-girl-with-xmas-present-santa-claus-flying-in-519218686.jpg");
    assets.setPreview(preview);

    shutterImage.setAssets(assets);

    return shutterImage;
  }
}
