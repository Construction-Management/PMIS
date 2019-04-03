package com.example.newu.photoupload;


public class Note {
    private String title;
    private String description;
    private String date;

    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}


