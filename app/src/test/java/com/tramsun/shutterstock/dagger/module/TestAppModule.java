package com.tramsun.shutterstock.dagger.module;

import android.content.Context;
import com.squareup.picasso.Picasso;
import com.tramsun.shutterstock.ShutterApp;
import com.tramsun.shutterstock.dagger.qualifier.ApplicationContext;
import com.tramsun.shutterstock.utils.ImageDownloader;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.mockito.Mockito;

@Module public final class TestAppModule {
  private final ShutterApp app;

  public TestAppModule() {
    this.app = Mockito.mock(ShutterApp.class);
  }

  @Provides @Singleton @ApplicationContext Context provideContext() {
    return app;
  }

  @Provides @Singleton Picasso providePicasso() {
    return null;
  }

  @Provides @Singleton ImageDownloader provideImageDownloader() {
    return Mockito.mock(ImageDownloader.class);
  }
}
