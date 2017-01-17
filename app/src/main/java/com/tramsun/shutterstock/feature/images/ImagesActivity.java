package com.tramsun.shutterstock.feature.images;

import android.app.ActivityOptions;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.squareup.picasso.Picasso;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.dagger.components.ActivityComponent;
import com.tramsun.shutterstock.databinding.ImagesActivityBinding;
import com.tramsun.shutterstock.feature.base.BaseActivity;
import com.tramsun.shutterstock.feature.detail.ImageDetailActivity;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import com.tramsun.shutterstock.ui.GridSpacingItemDecoration;
import com.tramsun.shutterstock.utils.rx.BgSingleOperation;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

import static com.tramsun.shutterstock.AppConstants.PER_PAGE_SIZE;

public class ImagesActivity extends BaseActivity<ImagesActivityBinding, ImagesViewModel>
    implements ImagesAdapter.OnImageClicked {

  private GridLayoutManager layoutManager;
  private ImagesAdapter adapter;
  private AtomicBoolean fetchInProgress = new AtomicBoolean(false);

  @Inject Picasso picasso;
  private int gridSpan;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setupRecyclerView();
  }

  private void setupRecyclerView() {
    gridSpan = 3;

    adapter = new ImagesAdapter(this, picasso, viewModel.getImages());

    layoutManager = new GridLayoutManager(this, gridSpan);
    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        return adapter.getSpanSize(gridSpan, position);
      }
    });
    binding.imagesList.setHasFixedSize(true);
    binding.imagesList.setLayoutManager(layoutManager);

    int cellPadding = getResources().getDimensionPixelSize(R.dimen.images_cell_spacing);
    binding.imagesList.addItemDecoration(
        new GridSpacingItemDecoration(gridSpan, cellPadding, true));

    binding.imagesList.setAdapter(adapter);

    setupInfiniteScrollListener(binding.imagesList);
  }

  private void setupInfiniteScrollListener(RecyclerView imagesList) {
    imagesList.addOnScrollListener(new InfiniteScrollListener(PER_PAGE_SIZE, layoutManager) {
      @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {

        if (fetchInProgress.getAndSet(true)) {
          Timber.d("onScrolledToEnd: Loading in progress. Do Nothing");
          return;
        }

        binding.imagesList.post(() -> {
          adapter.showProgressBar(true);
          Subscription subscription =
              viewModel.fetchNextPage().compose(new BgSingleOperation<>()).subscribe(success -> {
                fetchInProgress.set(false);

                adapter.showProgressBar(false);
                if (success) {
                  adapter.setImages(viewModel.getImages());
                  binding.imagesList.scrollToPosition(firstVisibleItemPosition + gridSpan);
                } else {
                  ui.showSnackBar(R.string.failed_to_fetch_images);
                }
              });

          subscriptions.add(subscription);
        });
      }
    });
  }

  @Override protected int layoutId() {
    return R.layout.activity_images;
  }

  @Override protected void onComponentCreated(ActivityComponent component) {
    component.inject(this);
  }

  @Override public void onImageClicked(ShutterImage image, ImageView imageView, Bitmap bitmap) {
    Timber.d("onImageClicked");
    ActivityOptions options =
        ActivityOptions.makeThumbnailScaleUpAnimation(imageView, bitmap, 0, 0);

    startActivity(ImageDetailActivity.getImageIntent(this, image), options.toBundle());
  }

  @Override public void onImageFailed() {
    Timber.e("onImageFailed");
    ui.showSnackBar(R.string.failed_to_fetch_image);
  }
}