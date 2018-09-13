package edu.monash.libraryofalexandria;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

public class LibraryOfAlexandria extends Application {

    private static Context appContext;
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initializeDatabase();
    }

    private void initializeDatabase() {
        new InitializeDatabaseAsync().execute();
    }

    private static class InitializeDatabaseAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase.getInstance();
            return null;
        }
    }
}
