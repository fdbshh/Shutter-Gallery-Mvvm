package com.tramsun.shutterstock.feature.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tramsun.shutterstock.BR;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.DaggerComponentManager;
import com.tramsun.shutterstock.dagger.components.ActivityComponent;
import javax.inject.Inject;

public abstract class BaseActivity<B extends ViewDataBinding, V extends ViewModel>
    extends AppCompatActivity {

  @Inject protected V viewModel;

  protected B binding;
  ProgressDialog progressDialog;

  protected void initProgressDialog() {
    progressDialog = new ProgressDialog(this, R.style.CenterCircleProgressTheme);
    progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
    progressDialog.setCancelable(false);
  }

  public void showSnackBar(int stringId) {
    showSnackBar(getString(stringId));
  }

  public void showSnackBar(String msg) {
    Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_LONG).show();
  }

  public void showProgressBar(boolean show) {
    if (show) {
      progressDialog.show();
    } else {
      progressDialog.dismiss();
    }
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inject dependencies
    onComponentCreated(DaggerComponentManager.getActivityComponent(this));

    // Bind the view and bind the viewModel to layout
    bindContentView(layoutId());
  }

  @SuppressWarnings("unchecked") public void bindContentView(int layoutId) {
    binding = DataBindingUtil.setContentView(this, layoutId);

    viewModel.attach(this instanceof MvvmView ? (MvvmView) this : null);
    viewModel.attach();

    binding.setVariable(BR.viewModel, viewModel);
  }

  @Override protected void onResume() {
    super.onResume();

    viewModel.resume();
  }

  @Override protected void onPause() {
    super.onPause();

    viewModel.pause();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    viewModel.saveInstanceState(outState);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    viewModel.restoreInstanceState(savedInstanceState);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    viewModel.detach();

    if (progressDialog != null) {
      progressDialog.dismiss();
      progressDialog = null;
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @LayoutRes protected abstract int layoutId();

  protected abstract void onComponentCreated(ActivityComponent component);
}
