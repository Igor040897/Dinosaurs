package com.igor040897.dinosaurs.mvp.model.db;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.igor040897.dinosaurs.API.DinoDate.Dino_;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fanre on 11/24/2017.
 */

@Entity
public class DataModel {
    @NonNull @Getter @Setter
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Embedded @Getter @Setter
    private Dino_ dino;
}
