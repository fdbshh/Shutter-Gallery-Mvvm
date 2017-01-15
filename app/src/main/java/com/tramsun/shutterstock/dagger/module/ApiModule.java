package com.tramsun.shutterstock.dagger.module;

import android.app.Application;
import com.tramsun.shutterstock.BuildConfig;
import com.tramsun.shutterstock.remote.ShutterstockApi;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public final class ApiModule {
  private static final int NETWORK_REQUEST_TIMEOUT_SECONDS = 10;
  private static final String RESPONSE_CACHE_DIRECTORY = "response_cache";
  private static final long CACHE_SIZE = 10 * 1024 * 1024;

  @Provides @Singleton OkHttpClient provideOkHttpClient(Application app) {
    File responseCacheDirectory = new File(app.getCacheDir(), RESPONSE_CACHE_DIRECTORY);
    Cache cache = new Cache(responseCacheDirectory, CACHE_SIZE);

    OkHttpClient.Builder builder =
        new OkHttpClient.Builder().connectTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .cache(cache);

    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      builder.addInterceptor(loggingInterceptor);
    }

    return builder.build();
  }

  @Provides @Singleton Retrofit providesRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(okHttpClient)
        .baseUrl(BuildConfig.SHUTTERSTOCK_BASE_URL)
        .build();
  }

  @Provides @Singleton ShutterstockApi provideShutterstockApi(Retrofit retrofit) {
    return retrofit.create(ShutterstockApi.class);
  }
}
