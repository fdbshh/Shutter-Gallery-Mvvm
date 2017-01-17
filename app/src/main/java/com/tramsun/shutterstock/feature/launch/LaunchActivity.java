package com.tramsun.shutterstock.feature.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.components.ActivityComponent;
import com.tramsun.shutterstock.databinding.LaunchActivityBinding;
import com.tramsun.shutterstock.feature.base.BaseActivity;

public class LaunchActivity extends BaseActivity<LaunchActivityBinding, LaunchViewModel> {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    animateLogo();
  }

  private void animateLogo() {
    binding.logoContainer.animate()
        .scaleX(2f)
        .scaleY(2f)
        .setDuration(500)
        .setInterpolator(new AccelerateDecelerateInterpolator())
        .withEndAction(this::animateAppName)
        .start();
  }

  private void animateAppName() {
    binding.appName.animate()
        .alpha(1)
        .setDuration(500)
        .setInterpolator(new AccelerateDecelerateInterpolator())
        .withEndAction(viewModel::onAnimationDone)
        .start();
  }

  @Override protected int layoutId() {
    return R.layout.activity_launch;
  }

  @Override protected void onComponentCreated(ActivityComponent component) {
    component.inject(this);
  }
}
