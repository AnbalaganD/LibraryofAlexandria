package edu.anbu.libraryofalexandria

import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        private const val DATABASE_NAME = "BookDatabase.db"

        @JvmStatic
        @get:Synchronized
        val instance: AppDatabase by lazy {
            databaseBuilder(
                LibraryOfAlexandria.appContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}