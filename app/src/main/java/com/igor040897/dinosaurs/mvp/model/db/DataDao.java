package com.igor040897.dinosaurs.mvp.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by fanre on 11/24/2017.
 */
@Dao
public interface DataDao {
    @Insert
    void insert(DataModel dataModel);

    @Delete
    void delete(DataModel dataModel);

    @Query("select * from DataModel")
    List<DataModel> getAllData();

    @Query("select * from DataModel where dino_title like :title")
    DataModel getByTitle(String title);
}
