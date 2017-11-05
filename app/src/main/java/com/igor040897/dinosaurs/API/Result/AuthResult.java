package com.igor040897.dinosaurs.API.Result;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.igor040897.dinosaurs.API.User;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 7/6/2017.
 */


public class AuthResult extends Result {

    @SerializedName("sessid")
    @Expose @Getter @Setter
    private String sessid;

    @SerializedName("session_name")
    @Expose @Getter @Setter
    private String session_name;

    @SerializedName("token")
    @Expose @Getter @Setter
    private String authToken;

    @SerializedName("user")
    @Expose @Getter @Setter
    private User user;

    public AuthResult() {}

    public AuthResult(String sessid, String session_name, String token) {
        this.sessid = sessid;
        this.session_name = session_name;
        this.authToken = token;
    }
}
