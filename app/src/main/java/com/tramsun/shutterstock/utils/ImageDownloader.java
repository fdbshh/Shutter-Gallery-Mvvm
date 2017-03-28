package com.tramsun.shutterstock.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class ImageDownloader {

  @Inject Picasso picasso;

  @Inject public ImageDownloader() {
  }

  public Single<Bitmap> download(String imageUrl) {
    return Single.create(emitter -> picasso.load(imageUrl).into(new Target() {

      @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        emitter.onSuccess(bitmap);
      }

      @Override public void onBitmapFailed(Drawable errorDrawable) {
        emitter.onError(new RuntimeException("Download failed"));
      }

      @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
      }
    }));
  }
}
