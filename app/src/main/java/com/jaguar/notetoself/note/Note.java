package com.jaguar.notetoself.note;

import androidx.annotation.NonNull;

import org.json.JSONObject;

public class Note {
    private String title;
    private String description;
    private boolean important;
    private boolean todo;
    private boolean idea;


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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean isTodo() {
        return todo;
    }

    public void setTodo(boolean todo) {
        this.todo = todo;
    }

    public boolean isIdea() {
        return idea;
    }

    public void setIdea(boolean idea) {
        this.idea = idea;
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
