package com.tramsun.shutterstock.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import com.tramsun.shutterstock.dagger.qualifier.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class StorageManager {
  private Context context;

  @Inject public StorageManager(@ApplicationContext Context context) {
    this.context = context;
  }

  public String saveImage(Bitmap bitmap, String fileName, String description) {
    return MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, fileName,
        description);
  }
}
