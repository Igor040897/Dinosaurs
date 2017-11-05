package com.igor040897.dinosaurs.API.DinoDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/3/2017.
 */

public class DinoImageRequest {
    @SerializedName("und")
    @Expose @Getter @Setter
    ArrayList<Fid> und;

    DinoImageRequest(){}

    public DinoImageRequest(ArrayList<Fid> und) {
        this.und = und;
    }

    public class Fid{
        @SerializedName("fid")
        @Expose @Getter @Setter
        String value;

        public Fid(){}

        public Fid(String value) {
            this.value = value;
        }
    }
}
