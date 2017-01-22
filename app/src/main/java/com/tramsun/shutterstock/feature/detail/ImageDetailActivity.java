package com.tramsun.shutterstock.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.components.ActivityComponent;
import com.tramsun.shutterstock.databinding.ImageDetailBinding;
import com.tramsun.shutterstock.feature.base.BaseActivity;
import com.tramsun.shutterstock.remote.models.ShutterImage;

public class ImageDetailActivity extends BaseActivity<ImageDetailBinding, ImageDetailViewModel> {

  private static final String INTENT_ARG_IMAGE = "image";

  public static Intent getImageIntent(Context context, ShutterImage image) {
    Intent intent = new Intent(context, ImageDetailActivity.class);
    intent.putExtra(INTENT_ARG_IMAGE, image);
    return intent;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding.imageDetail.setMaxScale(10f);
    binding.imageDetail.setDoubleTapZoomScale(10f);

    ShutterImage image = (ShutterImage) getIntent().getSerializableExtra(INTENT_ARG_IMAGE);
    viewModel.setImage(image);
  }

  @Override protected int layoutId() {
    return R.layout.activity_image_detail;
  }

  @Override protected void onComponentCreated(ActivityComponent component) {
    component.inject(this);
  }
}
