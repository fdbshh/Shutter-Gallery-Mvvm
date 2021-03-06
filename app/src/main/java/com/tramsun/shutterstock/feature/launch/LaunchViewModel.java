package com.tramsun.shutterstock.feature.launch;

import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.base.BaseViewModel;
import com.tramsun.shutterstock.feature.base.UiModule;
import com.tramsun.shutterstock.feature.base.navigator.Navigator;
import com.tramsun.shutterstock.feature.images.ImagesActivity;
import com.tramsun.shutterstock.remote.ShutterRepository;
import com.tramsun.shutterstock.utils.rx.BgOperation;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import timber.log.Timber;

@ActivityScope public class LaunchViewModel extends BaseViewModel {

  @VisibleForTesting boolean animationDone = false;
  @VisibleForTesting boolean workDone = false;
  @VisibleForTesting boolean workSuccess = false;

  @Inject ShutterRepository repository;
  @Inject UiModule ui;
  @Inject Navigator navigator;

  @Inject LaunchViewModel() {
  }

  @Override public void resume() {
    Disposable disposable =
        repository.fetchFirstImageSet().compose(new BgOperation<>()).subscribe(success -> {
          workDone = true;
          notifyChange();

          workSuccess = success;

          if (workSuccess) {
            goToNextScreen();
          } else {
            ui.showSnackBar(R.string.failed_to_fetch_images);
          }
        });

    disposables.add(disposable);
  }

  private void goToNextScreen() {
    if (animationDone && workDone && workSuccess) {
      Timber.d("goToNextScreen: Data fetched and animation finished. Moving to images screen");
      navigator.startActivity(ImagesActivity.class);
      navigator.finishActivity();
    } else {
      Timber.d(
          "goToNextScreen: Wait till both animation and work are done. workDone=%s, animationDone=%s",
          workDone, animationDone);
    }
  }

  void onAnimationDone() {
    animationDone = true;
    notifyChange();

    goToNextScreen();
  }

  public int getProgressBarVisibility() {
    return animationDone && !workDone ? View.VISIBLE : View.INVISIBLE;
  }
}
