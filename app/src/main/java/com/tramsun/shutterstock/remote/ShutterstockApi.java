package com.tramsun.shutterstock.remote;

import com.tramsun.shutterstock.remote.models.ShutterImages;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Single;

public interface ShutterstockApi {
  @GET("images/search") Single<ShutterImages> getImages(
      @Header("Authorization") String authorization, @Query("page") int page,
      @Query("per_page") int perPage);
}
