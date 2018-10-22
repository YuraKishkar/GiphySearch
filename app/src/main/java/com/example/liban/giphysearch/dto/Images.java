package com.example.liban.giphysearch.dto;

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
    @SerializedName("preview_gif")
    @Expose
    private Preview_gif preview_gif;
    @SerializedName("fixed_height_downsampled")
    @Expose
    private FixedHeightDownsampled fixedHeightDownsampled;

    public FixedHeightDownsampled getFixedHeightDownsampled() {
        return fixedHeightDownsampled;
    }

    public void setFixedHeightDownsampled(FixedHeightDownsampled fixedHeightDownsampled) {
        this.fixedHeightDownsampled = fixedHeightDownsampled;
    }

    public Preview_gif getPreview_gif() {
        return preview_gif;
    }

    public void setPreview_gif(Preview_gif preview_gif) {
        this.preview_gif = preview_gif;
    }

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
