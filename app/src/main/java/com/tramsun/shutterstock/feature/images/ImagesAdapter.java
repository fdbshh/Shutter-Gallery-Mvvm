package com.tramsun.shutterstock.feature.images;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tramsun.shutterstock.R;
import com.tramsun.shutterstock.databinding.ImageThumbBinding;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import timber.log.Timber;

class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.BaseViewHolder> {
  private static final int TYPE_ITEM = 0;
  private static final int TYPE_FOOTER = 1;

  private final OnImageClicked listener;
  private final Picasso picasso;
  private LayoutInflater inflater;

  private List<ShutterImage> images = new ArrayList<>();
  private final ShutterImage loader = new ShutterImage();

  private AtomicBoolean isPreviewLoadInProgress = new AtomicBoolean(false);

  void showProgressBar(boolean show) {
    if (show) {
      images.add(loader);
      notifyItemInserted(images.size() - 1);
    } else {
      images.remove(loader);
      notifyItemRemoved(images.size());
    }
  }

  int getSpanSize(int gridSpan, int position) {
    return getItemViewType(position) == TYPE_FOOTER ? gridSpan : 1;
  }

  interface OnImageClicked {
    void onImageClicked(ShutterImage image, ImageView imageView, Bitmap bitmap);

    void onImageFailed();
  }

  ImagesAdapter(OnImageClicked listener, Picasso picasso, @NonNull List<ShutterImage> images) {
    this.listener = listener;
    this.picasso = picasso;
    this.images.addAll(images);
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);

    inflater = LayoutInflater.from(recyclerView.getContext());
  }

  public void setImages(List<ShutterImage> images) {
    int prevSize = this.images.size();

    this.images.clear();
    this.images.addAll(images);

    notifyItemRangeInserted(prevSize, images.size() - 1);
  }

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_FOOTER) {
      return new BaseViewHolder(inflater.inflate(R.layout.progress_item, parent, false));
    } else {
      return new ItemViewHolder(ImageThumbBinding.inflate(inflater, parent, false));
    }
  }

  @Override public int getItemViewType(int position) {
    if (images.get(position) == loader) {
      return TYPE_FOOTER;
    } else {
      return TYPE_ITEM;
    }
  }

  @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
    ShutterImage image = images.get(position);

    if (getItemViewType(position) == TYPE_ITEM) {
      ImageThumbBinding binding = ((ItemViewHolder) holder).binding;
      picasso.load(image.getAssets().getLargeThumb().getUrl())
          .placeholder(R.drawable.placeholder)
          .into(binding.imageThumb);

      holder.itemView.setOnClickListener(v -> onImageClicked(binding, image));
    }
  }

  private void onImageClicked(ImageThumbBinding binding, ShutterImage image) {
    if (isPreviewLoadInProgress.getAndSet(true)) {
      Timber.d("onImageClicked: Load in progress. Ignore click");
      return;
    }

    Target target = new Target() {
      private void onComplete() {
        binding.setLoading(false);
        binding.imageThumb.setTag(null);
        isPreviewLoadInProgress.set(false);
      }

      @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        onComplete();
        listener.onImageClicked(image, binding.imageThumb, bitmap);
      }

      @Override public void onBitmapFailed(Drawable errorDrawable) {
        onComplete();
        listener.onImageFailed();
      }

      @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
      }
    };

    binding.setLoading(true);
    binding.imageThumb.setTag(target);
    picasso.load(image.getAssets().getPreview().getUrl()).into(target);
  }

  @Override public int getItemCount() {
    return images.size();
  }

  static class BaseViewHolder extends RecyclerView.ViewHolder {

    BaseViewHolder(View itemView) {
      super(itemView);
    }
  }

  private static class ItemViewHolder extends BaseViewHolder {

    private final ImageThumbBinding binding;

    ItemViewHolder(ImageThumbBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
