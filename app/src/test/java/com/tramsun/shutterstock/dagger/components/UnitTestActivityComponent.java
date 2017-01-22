package com.tramsun.shutterstock.dagger.components;

import com.tramsun.shutterstock.dagger.module.TestActivityModule;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import com.tramsun.shutterstock.feature.detail.ImageDetailViewModelTest;
import com.tramsun.shutterstock.feature.images.ImagesViewModelTest;
import com.tramsun.shutterstock.feature.launch.LaunchViewModelTest;
import dagger.Component;

@ActivityScope
@Component(dependencies = UnitTestAppComponent.class, modules = { TestActivityModule.class })
public interface UnitTestActivityComponent {
  void inject(LaunchViewModelTest launchViewModelTest);

  void inject(ImagesViewModelTest imagesViewModelTest);

  void inject(ImageDetailViewModelTest imageDetailViewModelTest);
}
