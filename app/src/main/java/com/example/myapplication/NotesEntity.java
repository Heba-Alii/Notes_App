package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class NotesEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String courseName;
    private String courseDesc;
    private boolean isFavorite;



    public NotesEntity(String courseName, String courseDesc, boolean isFavorite) {
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.isFavorite = isFavorite;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
