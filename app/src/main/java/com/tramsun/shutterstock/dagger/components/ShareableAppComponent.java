package com.tramsun.shutterstock.dagger.components;

import android.content.Context;
import android.content.res.Resources;
import com.squareup.picasso.Picasso;
import com.tramsun.shutterstock.dagger.qualifier.ApplicationContext;
import com.tramsun.shutterstock.remote.ShutterRepository;
import com.tramsun.shutterstock.remote.ShutterstockApi;

interface ShareableAppComponent {

  @ApplicationContext Context provideContext();

  Resources provideResources();

  ShutterstockApi provideShutterstockApi();

  ShutterRepository provideShutterRepository();

  Picasso providePicasso();
}
