package com.tramsun.shutterstock.dagger.components;

import com.squareup.picasso.Picasso;
import com.tramsun.shutterstock.remote.ShutterRepository;

interface ShareableAppComponent {

  ShutterRepository provideShutterRepository();

  Picasso providePicasso();
}
