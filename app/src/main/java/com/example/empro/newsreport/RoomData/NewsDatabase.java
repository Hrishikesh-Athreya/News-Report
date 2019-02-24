package com.example.empro.newsreport.RoomData;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;


@Database(entities = {NewsDataModelClass.class},version = 1,exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract DAO DAO();
    private static NewsDatabase INSTANCE;
    static NewsDatabase getNewsDatabase(final Context context){
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, NewsDatabase.class, "news_db").fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }
}

