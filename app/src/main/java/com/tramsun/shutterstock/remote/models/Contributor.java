package com.tramsun.shutterstock.remote.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Contributor implements Serializable {

  @SerializedName("id") private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
