package com.example.liban.giphysearch.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liban on 15.08.2018.
 */

public class ListData {
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


}
