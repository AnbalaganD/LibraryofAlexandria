package edu.monash.libraryofalexandria;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BookDatabase.db";
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance() {
        if(instance == null)
            instance = Room.databaseBuilder(LibraryOfAlexandria.getAppContext(), AppDatabase.class, DATABASE_NAME).build();
        return instance;
    }

    public abstract BookDao bookDao();
}
