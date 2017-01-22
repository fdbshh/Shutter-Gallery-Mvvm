package com.tramsun.shutterstock.feature.detail;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.android.annotations.VisibleForTesting;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.BaseViewModel;
import com.tramsun.shutterstock.feature.base.UiModule;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import com.tramsun.shutterstock.utils.StorageManager;
import javax.inject.Inject;

@ActivityScope public class ImageDetailViewModel extends BaseViewModel {

  @VisibleForTesting ShutterImage image;

  @Inject RxPermissions rxPermissions;
  @Inject UiModule uiModule;
  @Inject StorageManager storageManager;
  @Inject Picasso picasso;

  @Inject public ImageDetailViewModel() {
  }

  public void setImage(ShutterImage image) {
    this.image = image;
    notifyChange();
  }

  public String getImageUrl() {
    return image.getAssets().getPreview().getUrl();
  }

  public void downloadImage() {
    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(granted -> {
      if (granted) {
        uiModule.showToast(R.string.downloading);

        picasso.load(getImageUrl()).into(new Target() {

          @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            String path = storageManager.saveImage(bitmap, image.getId(), image.getDescription());
            if (path == null) {
              downloadImageFailed();
            }
          }

          @Override public void onBitmapFailed(Drawable errorDrawable) {
            downloadImageFailed();
          }

          @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
          }
        });
      }
    });
  }

  private void downloadImageFailed() {
    uiModule.showToast(R.string.download_failed);
  }
}
