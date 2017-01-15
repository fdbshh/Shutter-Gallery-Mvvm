package com.tramsun.shutterstock.remote.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ShutterImages {

  @SerializedName("page") private Integer page;
  @SerializedName("per_page") private Integer perPage;
  @SerializedName("total_count") private Integer totalCount;
  @SerializedName("search_id") private String searchId;
  @SerializedName("data") private List<ShutterImage> data;

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPerPage() {
    return perPage;
  }

  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public String getSearchId() {
    return searchId;
  }

  public void setSearchId(String searchId) {
    this.searchId = searchId;
  }

  public List<ShutterImage> getData() {
    return data;
  }

  public void setData(List<ShutterImage> data) {
    this.data = data;
  }
}
