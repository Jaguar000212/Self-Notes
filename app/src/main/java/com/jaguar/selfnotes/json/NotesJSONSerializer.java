package com.jaguar.selfnotes.json;

import android.content.Context;
import android.util.Log;

import com.jaguar.selfnotes.note.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class NotesJSONSerializer {
    private final String filename;
    private final Context context;

    public NotesJSONSerializer(String filename, Context context) {
        this.filename = filename;
        this.context = context;
    }

    public void saveNotes(List<Note> notes) throws IOException {
        JSONArray jsonArray = new JSONArray();

        for (Note note : notes) {
            jsonArray.put(note.toJSON());
        }

        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Note> loadNotes() throws IOException, JSONException {
        ArrayList<Note> notes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(filename);
            reader = new BufferedReader(new java.io.InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < jArray.length(); i++) {
                notes.add(new Note(jArray.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Log.e("Note to self", "(Might be a first start) Error loading notes: ", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return notes;
    }
}
