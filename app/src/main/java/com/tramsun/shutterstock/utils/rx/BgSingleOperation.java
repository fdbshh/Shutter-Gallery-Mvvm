package com.tramsun.shutterstock.utils.rx;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BgSingleOperation<T> implements Single.Transformer<T, T> {

  @Override public Single<T> call(Single<T> tSingle) {
    return tSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}