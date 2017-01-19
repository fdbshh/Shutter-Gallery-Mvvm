package com.tramsun.shutterstock.dagger.components;

import android.content.Context;
import android.content.res.Resources;
import com.squareup.picasso.Picasso;
import com.tramsun.shutterstock.dagger.qualifier.ApplicationContext;
import com.tramsun.shutterstock.remote.ShutterApi;
import com.tramsun.shutterstock.remote.ShutterRepository;

interface ShareableAppComponent {

  @ApplicationContext Context provideContext();

  Resources provideResources();

  ShutterApi provideShutterstockApi();

  ShutterRepository provideShutterRepository();

  Picasso providePicasso();
}
