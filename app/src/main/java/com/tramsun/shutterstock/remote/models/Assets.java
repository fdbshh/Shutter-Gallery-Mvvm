package com.tramsun.shutterstock.remote.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Assets implements Serializable {

  @SerializedName("preview") private Preview preview;
  @SerializedName("small_thumb") private SmallThumb smallThumb;
  @SerializedName("large_thumb") private LargeThumb largeThumb;

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
