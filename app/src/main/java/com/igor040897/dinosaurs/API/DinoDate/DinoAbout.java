package com.igor040897.dinosaurs.API.DinoDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/5/2017.
 */

public class DinoAbout {
    @SerializedName("und")
    @Expose @Getter @Setter
    ArrayList<ValueAbout> und;

    DinoAbout(){}

    public DinoAbout(ArrayList<ValueAbout> und) {
        this.und = und;
    }

    public class ValueAbout{
        @SerializedName("value")
        @Expose @Getter @Setter
        String value;

        public ValueAbout(){}

        public ValueAbout(String value) {
            this.value = value;
        }
    }
}
