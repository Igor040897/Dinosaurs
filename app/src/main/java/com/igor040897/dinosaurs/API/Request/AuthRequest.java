package com.igor040897.dinosaurs.API.Request;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by fanre on 11/2/2017.
 */
@JsonObject
public class AuthRequest {
    @JsonField(name = "username")
    private String username;
    @JsonField(name = "password")
    private String password;

    public AuthRequest(){}

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
