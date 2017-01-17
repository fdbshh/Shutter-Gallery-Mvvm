package com.tramsun.shutterstock.feature.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import com.tramsun.shutterstock.dagger.scope.ActivityScope;
import javax.inject.Inject;

@ActivityScope public class UiModule {
  private Activity activity;

  @Inject UiModule(Activity activity) {
    this.activity = activity;
  }

  private ProgressDialog progressDialog;

  public void initProgressDialog(int stringId) {
    progressDialog = new ProgressDialog(activity);
    progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
    progressDialog.setCancelable(false);
    progressDialog.setMessage(activity.getString(stringId));
  }

  public void showSnackBar(int stringId) {
    showSnackBar(activity.getString(stringId));
  }

  private void showSnackBar(String msg) {
    Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
  }

  public void showProgressBar(boolean show) {
    if (show) {
      progressDialog.show();
    } else {
      progressDialog.dismiss();
    }
  }

  void destroy() {
    if (progressDialog != null) {
      progressDialog.dismiss();
      progressDialog = null;
    }
  }
}
