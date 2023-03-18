package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNotes(NotesEntity notes);

    @Query("select * from notes_table")
    List<NotesEntity> getAllNotes();

    @Query("select * from notes_table where isFavorite=1")
    List<NotesEntity> getAllFavoriteItems();

    @Query("delete from notes_table")
    void deleteAllNotes();

    @Query("update notes_table SET isFavorite= :favorite WHERE id= :noteId  ")
    void update(boolean favorite, int noteId);
}