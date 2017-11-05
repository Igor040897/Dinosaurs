package com.igor040897.dinosaurs.API.Result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 10/30/2017.
 */

public class RegisterResult {

    @SerializedName("uid")
    @Expose @Getter @Setter
    private String uid;

    @SerializedName("uri")
    @Expose @Getter @Setter
    private String uri;

    public RegisterResult() {}

    public RegisterResult(String uid, String uri) {
        this.uid = uid;
        this.uri = uri;
    }
}
