package com.jaguar.notetoself;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    Note ntempNote = new Note("", "", false, false, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fab = findViewById(R.id.floatingAdd);
        fab.setOnClickListener(view -> {
            DialogFragment dialog = new NewNoteDialog();
            dialog.show(getSupportFragmentManager(), "");
        });

        Button showBtn = findViewById(R.id.showbtn);
        showBtn.setOnClickListener(view -> {
            ShowNoteDialog showNoteDialog = new ShowNoteDialog();
            showNoteDialog.sendNoteSelected(ntempNote);
            showNoteDialog.show(getSupportFragmentManager(), "");
        });
    }

    public void createNewNoteDialog(Note newNote) {
        ntempNote = newNote;
    }
}