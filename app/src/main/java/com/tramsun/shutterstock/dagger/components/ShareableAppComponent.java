package com.tramsun.shutterstock.dagger.components;

import com.squareup.picasso.Picasso;
import com.tramsun.shutterstock.remote.ShutterRepository;
import okhttp3.OkHttpClient;

interface ShareableAppComponent {

  ShutterRepository provideShutterRepository();

  Picasso providePicasso();

  OkHttpClient provideOkHttpClient();
}
