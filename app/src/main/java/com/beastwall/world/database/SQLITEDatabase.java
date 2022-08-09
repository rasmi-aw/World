package com.beastwall.world.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.beastwall.world.database.dao.CountryDAO;
import com.beastwall.world.database.model.CountryDB;

@Database(entities = {CountryDB.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class SQLITEDatabase extends RoomDatabase {
    //
    public abstract CountryDAO countryDAO();
}
