package com.igor040897.dinosaurs.API.DinoDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/5/2017.
 */

public class DinoDate {
    @SerializedName("und")
    @Expose @Getter @Setter
    ArrayList<Date> und;

    DinoDate() {
    }

    public DinoDate(ArrayList<Date> und) {
        this.und = und;
    }
}
