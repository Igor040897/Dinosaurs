package com.igor040897.dinosaurs.API.Result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.igor040897.dinosaurs.API.DinoDate.Dino;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/2/2017.
 */

public class DinosResult {
    @SerializedName("dinos")
    @Expose @Getter @Setter
    List<Dino> dinos = null;
}
