package com.example.task_native.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnedObject {

    @SerializedName("total_count")
    int total_count;
    @SerializedName("items")
    List<Repository> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
}
