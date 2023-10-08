package edu.anbu.libraryofalexandria;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
