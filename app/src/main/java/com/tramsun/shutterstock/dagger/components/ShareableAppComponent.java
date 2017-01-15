package com.tramsun.shutterstock.dagger.components;

import android.content.Context;
import android.content.res.Resources;
import com.tramsun.shutterstock.dagger.qualifier.ApplicationContext;

interface ShareableAppComponent {

  @ApplicationContext Context provideContext();

  Resources provideResources();
}
