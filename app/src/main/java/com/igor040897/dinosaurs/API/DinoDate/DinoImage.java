package com.igor040897.dinosaurs.API.DinoDate;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/2/2017.
 */

public class DinoImage {
    @SerializedName("src")
    @Expose @Getter @Setter
    private String src;

    @SerializedName("alt")
    @Expose @Getter @Setter
    private String alt;

    public DinoImage(String src, String alt) {
        this.src = src;
        this.alt = alt;
    }
}
