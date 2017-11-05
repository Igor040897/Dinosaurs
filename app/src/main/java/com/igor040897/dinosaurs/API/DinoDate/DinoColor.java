package com.igor040897.dinosaurs.API.DinoDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/4/2017.
 */

public class DinoColor {
    @SerializedName("und")
    @Expose @Getter @Setter
    ColorTid und;

    DinoColor(){}

    public DinoColor(ColorTid und) {
        this.und = und;
    }

    private class ColorTid {
        @SerializedName("tid")
        @Expose @Getter @Setter
        String tid;

        public ColorTid(){}

        public ColorTid(TidColor tid) {
            this.tid = String.valueOf(tid);
        }


    }
}
