package com.igor040897.dinosaurs.API.Request;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by fanre on 11/1/2017.
 */

@JsonObject
public class RegisterRequest {

    @JsonField(name = "name")
    private String name;
    @JsonField(name = "mail")
    private String mail;
    @JsonField(name = "pass")
    private String pass;

    public RegisterRequest(){}

    public RegisterRequest(String username, String mail, String password) {
        this.name = username;
        this.mail = mail;
        this.pass = password;
    }
}
