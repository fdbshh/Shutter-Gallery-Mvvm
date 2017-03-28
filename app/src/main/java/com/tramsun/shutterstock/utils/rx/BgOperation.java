package com.tramsun.shutterstock.utils.rx;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BgOperation<T> implements SingleTransformer<T, T> {

  @Override public SingleSource<T> apply(Single<T> upstream) {
    return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}