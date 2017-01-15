package com.tramsun.shutterstock.remote.models;

import com.google.gson.annotations.SerializedName;

public class ShutterImage {

  @SerializedName("id") private String id;
  @SerializedName("aspect") private Double aspect;
  @SerializedName("assets") private Assets assets;
  @SerializedName("contributor") private Contributor contributor;
  @SerializedName("description") private String description;
  @SerializedName("image_type") private String imageType;
  @SerializedName("media_type") private String mediaType;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Double getAspect() {
    return aspect;
  }

  public void setAspect(Double aspect) {
    this.aspect = aspect;
  }

  public Assets getAssets() {
    return assets;
  }

  public void setAssets(Assets assets) {
    this.assets = assets;
  }

  public Contributor getContributor() {
    return contributor;
  }

  public void setContributor(Contributor contributor) {
    this.contributor = contributor;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }
}
