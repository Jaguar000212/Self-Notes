package com.jaguar.notetoself;

import androidx.annotation.NonNull;

public class Note {
    private final String title;
    private final String description;
    private final boolean important;
    private final boolean todo;
    private final boolean idea;


    public Note(String title, String description, boolean important, boolean todo, boolean idea) {
        this.title = title;
        this.description = description;
        this.important = important;
        this.todo = todo;
        this.idea = idea;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isImportant() {
        return important;
    }

    public boolean isTodo() {
        return todo;
    }

    public boolean isIdea() {
        return idea;
    }

    @NonNull
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nImportant: " + important + "\nTodo: " + todo + "\nIdea: " + idea;
    }
}
