package com.jaguar.notetoself.note;

import androidx.annotation.NonNull;

import org.json.JSONObject;

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

    public Note(JSONObject jsonObject) {
        this.title = jsonObject.optString("title");
        this.description = jsonObject.optString("description");
        this.important = jsonObject.optBoolean("important");
        this.todo = jsonObject.optBoolean("todo");
        this.idea = jsonObject.optBoolean("idea");
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

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("important", important);
            jsonObject.put("todo", todo);
            jsonObject.put("idea", idea);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
