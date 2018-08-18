package com.example.liban.giphysearch.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liban on 15.08.2018.
 */

public class Images {
    @SerializedName("fixed_height")
    @Expose
    private FixedHeight fixedHeight;

    @SerializedName("original")
    @Expose
    private Original original;

    public Original getOriginal() {
        return original;
    }

    public FixedHeight getFixedHeight() {
        return fixedHeight;
    }

    public void setFixedHeight(FixedHeight fixedHeight) {
        this.fixedHeight = fixedHeight;
    }

}
