package com.igor040897.dinosaurs.API.DinoDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/2/2017.
 */

public class Dino {
    @SerializedName("dino")
    @Expose @Getter @Setter
    private Dino_ dino;

    public Dino(Dino_ dino) {
        this.dino = dino;
    }
}
