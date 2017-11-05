package com.igor040897.dinosaurs.API.Result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/5/2017.
 */

public class LoadImageResult {
    @SerializedName("fid")
    @Expose @Getter @Setter
    private String fid;
    @SerializedName("uri")
    @Expose @Getter @Setter
    private String uri;
}
