package com.reivai.livetest.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @Expose
    @SerializedName("support")
    public Support support;
    @Expose
    @SerializedName("data")
    public List<Data> data;
    @Expose
    @SerializedName("total_pages")
    public int total_pages;
    @Expose
    @SerializedName("total")
    public int total;
    @Expose
    @SerializedName("per_page")
    public int per_page;
    @Expose
    @SerializedName("page")
    public int page;

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
