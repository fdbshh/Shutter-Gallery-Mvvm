package com.tramsun.shutterstock.remote.models;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;

public class Contributor {

  @SerializedName("id") private String id;

  protected Contributor(Parcel in) {
    id = in.readString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
