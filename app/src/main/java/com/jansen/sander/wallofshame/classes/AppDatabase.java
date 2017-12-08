package com.jansen.sander.wallofshame.classes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jansen.sander.wallofshame.interfaces.ShameDao;

/**
 * Created by Sander on 5/12/2017.
 */

@Database(entities = {Shame.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance = null;

    // This is a singleton
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "Shames").build();
        }

        return instance;
    }

    public static void destroy() {
        // Works because of the garbage collector
        instance = null;
    }


    public abstract ShameDao shameDao();
}
