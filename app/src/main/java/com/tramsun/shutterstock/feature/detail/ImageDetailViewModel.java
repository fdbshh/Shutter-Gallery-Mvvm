package com.tramsun.shutterstock.feature.detail;

import android.Manifest;
import android.support.annotation.VisibleForTesting;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.BaseViewModel;
import com.tramsun.shutterstock.feature.base.UiModule;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import com.tramsun.shutterstock.utils.ImageDownloader;
import com.tramsun.shutterstock.utils.StorageManager;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

@ActivityScope public class ImageDetailViewModel extends BaseViewModel {

  @VisibleForTesting ShutterImage image;

  @Inject RxPermissions rxPermissions;
  @Inject UiModule uiModule;
  @Inject StorageManager storageManager;
  @Inject ImageDownloader downloader;

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

        Disposable disposable = downloader.download(getImageUrl()).subscribe(bitmap -> {
          String path = storageManager.saveImage(bitmap, image.getId(), image.getDescription());
          if (path == null) {
            downloadImageFailed();
          }
        }, e -> downloadImageFailed());
        disposables.add(disposable);
      }
    });
  }

  private void downloadImageFailed() {
    uiModule.showToast(R.string.download_failed);
  }
}
