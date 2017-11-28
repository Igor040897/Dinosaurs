package com.igor040897.dinosaurs.mvp.model.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by fanre on 11/24/2017.
 */
@Database(entities = {DataModel.class}, version = 3, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract DataDao getDataDao();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
