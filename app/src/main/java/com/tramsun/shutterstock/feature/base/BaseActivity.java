package com.tramsun.shutterstock.feature.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tramsun.shutterstock.BR;
import com.tramsun.shutterstock.dagger.DaggerComponentManager;
import com.tramsun.shutterstock.dagger.components.ActivityComponent;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity<B extends ViewDataBinding, V extends ViewModel>
    extends AppCompatActivity {

  @Inject protected UiModule ui;

  @Inject protected V viewModel;

  protected B binding;

  protected CompositeSubscription subscriptions = new CompositeSubscription();

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

    subscriptions.unsubscribe();

    viewModel.detach();

    ui.destroy();
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
