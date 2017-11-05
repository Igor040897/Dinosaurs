package com.igor040897.dinosaurs.API.DinoDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/5/2017.
 */

public class Dino_ {
    @SerializedName("dino_title")
    @Expose @Getter @Setter
    private String dino_title;

    @SerializedName("dino_color")
    @Expose @Getter @Setter
    private String dinoColor;

    @SerializedName("dino_birthdate")
    @Expose @Getter @Setter
    private String dinoBirthdate;

    @SerializedName("dino_image")
    @Expose @Getter @Setter
    private DinoImage dinoImage;

    @SerializedName("dino_about")
    @Expose @Getter @Setter
    private String dinoAbout;

    Dino_(){}

    public Dino_(String dino_title, String dinoColor, String dinoBirthdate, DinoImage dinoImage, String dinoAbout) {
        this.dino_title = dino_title;
        this.dinoColor = dinoColor;
        this.dinoBirthdate = dinoBirthdate;
        this.dinoImage = dinoImage;
        this.dinoAbout = dinoAbout;
    }
}
