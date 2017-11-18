package com.igor040897.dinosaurs.API.Request;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.igor040897.dinosaurs.API.DinoDate.DinoAbout;
import com.igor040897.dinosaurs.API.DinoDate.DinoColor;
import com.igor040897.dinosaurs.API.DinoDate.DinoDate;
import com.igor040897.dinosaurs.API.DinoDate.DinoImageRequest;

/**
 * Created by fanre on 11/3/2017.
 */
@JsonObject
public class AddDinoRequest {
    @JsonField(name = "title")
    private String dino_name;

    @JsonField(name = "status")
    private String status = "1";

    @JsonField(name = "name")
    private String username;

    @JsonField(name = "type")
    private String type = "dino";

    @JsonField(name = "field_dino_color")
    private DinoColor dino_color;

    @JsonField(name = "field_dino_about")
    private DinoAbout dino_about;

    @JsonField(name = "field_dino_birth_date")
    private DinoDate dino_birth_date;

    @JsonField(name = "field_dino_image")
    private DinoImageRequest dino_image;

    public AddDinoRequest(String dino_name, String username,
                          DinoColor dino_color, DinoAbout dino_about,
                          DinoDate dino_birth_date, DinoImageRequest dino_image) {
        this.dino_name = dino_name;
        this.username = username;
        this.dino_color = dino_color;
        this.dino_about = dino_about;
        this.dino_birth_date = dino_birth_date;
        this.dino_image = dino_image;
    }
}
