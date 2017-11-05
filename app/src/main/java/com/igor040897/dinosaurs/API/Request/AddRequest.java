package com.igor040897.dinosaurs.API.Request;

import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.igor040897.dinosaurs.API.DinoDate.DinoAbout;
import com.igor040897.dinosaurs.API.DinoDate.DinoColor;
import com.igor040897.dinosaurs.API.DinoDate.DinoDate;
import com.igor040897.dinosaurs.API.DinoDate.DinoImageRequest;

/**
 * Created by fanre on 11/3/2017.
 */
@JsonObject
public class AddRequest {
    private String title;
    private String status = "1";
    private String name;
    private String type = "dino";
    private DinoColor field_dino_color;
    private DinoAbout field_dino_about;
    private DinoDate field_dino_birth_date;
    private DinoImageRequest field_dino_image;

}
