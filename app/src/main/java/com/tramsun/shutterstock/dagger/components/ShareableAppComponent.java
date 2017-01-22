package com.tramsun.shutterstock.dagger.components;

import com.squareup.picasso.Picasso;
import com.tramsun.shutterstock.remote.ShutterRepository;
import com.tramsun.shutterstock.utils.ImageDownloader;
import com.tramsun.shutterstock.utils.StorageManager;
import okhttp3.OkHttpClient;

interface ShareableAppComponent {

  ShutterRepository provideShutterRepository();

  Picasso providePicasso();

  OkHttpClient provideOkHttpClient();

  StorageManager provideStorageManager();

  ImageDownloader provideImageDownloader();
}
