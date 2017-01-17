package com.tramsun.shutterstock.feature.images;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tramsun.shutterstock.databinding.ImageThumbBinding;
import com.tramsun.shutterstock.remote.models.ShutterImage;
import java.util.List;

class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
  private final OnImageClicked listener;
  private LayoutInflater inflater;

  private final Picasso picasso;
  private List<ShutterImage> images;

  public interface OnImageClicked {
    void onImageClicked(ShutterImage image, ImageView imageView, Bitmap bitmap);

    void onImageFailed();
  }

  ImagesAdapter(OnImageClicked listener, Picasso picasso, @NonNull List<ShutterImage> images) {
    this.listener = listener;
    this.picasso = picasso;
    this.images = images;
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);

    inflater = LayoutInflater.from(recyclerView.getContext());
  }

  public void setImages(List<ShutterImage> images) {
    this.images = images;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(ImageThumbBinding.inflate(inflater, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    ShutterImage image = images.get(position);

    picasso.load(image.getAssets().getLargeThumb().getUrl()).into(holder.binding.imageThumb);

    holder.itemView.setOnClickListener(v -> onImageClicked(holder.binding, image));
  }

  private void onImageClicked(ImageThumbBinding binding, ShutterImage image) {
    Target target = new Target() {
      private void onComplete() {
        binding.setLoading(false);
        binding.imageThumb.setTag(null);
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

  class ViewHolder extends RecyclerView.ViewHolder {

    private final ImageThumbBinding binding;

    ViewHolder(ImageThumbBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
