package com.beastwall.world.database;

import android.content.Context;

import androidx.room.Room;

/**
 * @author AbdelWadoud Rasmi
 * plays the role of a singleton db manager
 */
public class DataBase {
    private static SQLITEDatabase database;

    private DataBase() {
    }

    public static SQLITEDatabase get(Context context) {
        if (database == null)
            database = Room.databaseBuilder(context, SQLITEDatabase.class, "app-db").build();
        return database;
    }
}
