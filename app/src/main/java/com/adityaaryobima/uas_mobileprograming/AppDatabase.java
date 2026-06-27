package com.adityaaryobima.uas_mobileprograming;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Endemik.class, Favorit.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EndemikDao endemikDao();
    public abstract FavoritDao favoritDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "endemikDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
