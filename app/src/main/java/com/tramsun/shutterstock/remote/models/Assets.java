package com.tramsun.shutterstock.remote.models;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;

public class Assets {

  @SerializedName("preview") private Preview preview;
  @SerializedName("small_thumb") private SmallThumb smallThumb;
  @SerializedName("large_thumb") private LargeThumb largeThumb;

  protected Assets(Parcel in) {
  }

  public Preview getPreview() {
    return preview;
  }

  public void setPreview(Preview preview) {
    this.preview = preview;
  }

  public SmallThumb getSmallThumb() {
    return smallThumb;
  }

  public void setSmallThumb(SmallThumb smallThumb) {
    this.smallThumb = smallThumb;
  }

  public LargeThumb getLargeThumb() {
    return largeThumb;
  }

  public void setLargeThumb(LargeThumb largeThumb) {
    this.largeThumb = largeThumb;
  }
}
