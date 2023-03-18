package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = NotesEntity.class, version = 1)
public abstract class NotesBuilder extends RoomDatabase {
    private static NotesBuilder instanceDB;

    public abstract NotesDao notesDao();

    public static NotesBuilder getInstance(Context context) {
        if (instanceDB == null) {
            instanceDB = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NotesBuilder.class, "noteDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanceDB;
    }

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
