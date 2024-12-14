package com.jaguar.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ShowNoteDialog extends DialogFragment {
    private Note note;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.show_note_dialog, null);

        TextView description = dialogView.findViewById(R.id.snoteDescription);
        description.setText(note.getDescription());

        TextView important = dialogView.findViewById(R.id.isImportant);
        if (!note.isImportant()) {
            important.setVisibility(View.GONE);
        }

        TextView todo = dialogView.findViewById(R.id.isToDo);
        if (!note.isTodo()) {
            todo.setVisibility(View.GONE);
        }

        TextView idea = dialogView.findViewById(R.id.isIdea);
        if (!note.isIdea()) {
            idea.setVisibility(View.GONE);
        }

        Button buttonOK = dialogView.findViewById(R.id.btn_OK);
        buttonOK.setOnClickListener(v -> dismiss());

        builder.setView(dialogView).setTitle(note.getTitle());
        return builder.create();
    }

    public void sendNoteSelected(Note noteSelected) {
        note = noteSelected;
    }
}
