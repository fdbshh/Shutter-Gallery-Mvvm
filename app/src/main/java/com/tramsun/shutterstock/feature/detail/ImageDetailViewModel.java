package com.tramsun.shutterstock.feature.detail;

import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.BaseViewModel;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import javax.inject.Inject;

@ActivityScope public class ImageDetailViewModel extends BaseViewModel {

  private ShutterImage image;

  @Inject public ImageDetailViewModel() {
  }

  public void setImage(ShutterImage image) {
    this.image = image;
    notifyChange();
  }

  public String getImageUrl() {
    return image.getAssets().getPreview().getUrl();
  }
}
