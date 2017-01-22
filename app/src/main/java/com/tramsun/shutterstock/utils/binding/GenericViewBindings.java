package com.tramsun.shutterstock.utils.binding;

import android.databinding.BindingAdapter;
import android.view.View;

public class GenericViewBindings {
  @BindingAdapter("onLongClick")
  public static void onLongClickListener(View view, Runnable runnable) {
    view.setOnLongClickListener(v -> {
      if (runnable != null) {
        runnable.run();
        return true;
      }

      return false;
    });
  }
}
