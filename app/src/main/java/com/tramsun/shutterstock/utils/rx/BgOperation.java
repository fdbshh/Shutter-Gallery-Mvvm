package com.tramsun.shutterstock.utils.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BgOperation<T> implements Observable.Transformer<T, T> {
  @Override public Observable<T> call(Observable<T> observable) {
    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}