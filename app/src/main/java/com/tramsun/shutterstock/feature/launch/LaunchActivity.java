package com.tramsun.shutterstock.feature.launch;

import android.os.Bundle;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.components.ActivityComponent;
import com.tramsun.shutterstock.databinding.LaunchActivityBinding;
import com.tramsun.shutterstock.feature.base.BaseActivity;

public class LaunchActivity extends BaseActivity<LaunchActivityBinding, LaunchViewModel> {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int layoutId() {
    return R.layout.activity_launch;
  }

  @Override protected void onComponentCreated(ActivityComponent component) {
    component.inject(this);
  }
}
