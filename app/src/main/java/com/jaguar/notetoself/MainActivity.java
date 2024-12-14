package com.jaguar.notetoself;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    final HashMap<String, Note> notes = new HashMap<>();
    int size = 0;

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

        updateScrollView();

        FloatingActionButton fab = findViewById(R.id.floatingAdd);
        fab.setOnClickListener(view -> {
            DialogFragment dialog = new NewNoteDialog();
            dialog.show(getSupportFragmentManager(), "");
        });
    }

    public void createNewNoteDialog(Note newNote) {
        notes.put(newNote.getTitle(), newNote);
        size++;
        updateScrollView();
    }

    public CardView createCardView(Note note) {
        TextView title = new TextView(this);
        title.setText(note.getTitle());
        title.setGravity(Gravity.START);
        title.setTextSize(30f);

        CardView card = new CardView(this);
        card.addView(title);
        card.setRadius(20);
        card.setContentPadding(15, 15, 15, 15);
        card.setCardElevation(10);
        card.setOnClickListener(view -> {
            ShowNoteDialog dialog = new ShowNoteDialog();
            dialog.sendNoteSelected(note);
            dialog.show(getSupportFragmentManager(), "");
        });
        return card;
    }

    public void updateScrollView() {
        LinearLayout view = findViewById(R.id.allNotes);

        if (view != null) Log.d("View", "ScrollView is not null");
        else {
            Log.e("View", "ScrollView is null");
            return;
        }
        TextView textView = findViewById(R.id.NoNotes);
        if (size == 0) {
            textView.setVisibility(TextView.VISIBLE);
            return;
        }
        view.removeAllViews();
        for (String key : notes.keySet()) {
            Note note = notes.get(key);

            FrameLayout frame = new FrameLayout(this);
            ViewGroup.MarginLayoutParams frameMargin = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            frameMargin.setMargins(0, 0, 0, 20);
            frame.setLayoutParams(frameMargin);
            assert note != null;
            CardView card = createCardView(note);
            frame.addView(card);
            view.addView(frame);
        }
    }
}