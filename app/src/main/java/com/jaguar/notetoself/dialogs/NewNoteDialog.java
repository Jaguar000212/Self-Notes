package com.jaguar.notetoself.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.jaguar.notetoself.MainActivity;
import com.jaguar.notetoself.R;
import com.jaguar.notetoself.note.Note;

import java.util.Objects;

public class NewNoteDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = dialogView.findViewById(R.id.noteTitle);
        final EditText editDescription = dialogView.findViewById(R.id.noteDescription);
        final CheckBox checkBoxIdea = dialogView.findViewById(R.id.noteIdea);
        final CheckBox checkBoxTodo = dialogView.findViewById(R.id.noteToDo);
        final CheckBox checkBoxImportant = dialogView.findViewById(R.id.noteImportant);
        final Button buttonAdd = dialogView.findViewById(R.id.noteAdd);
        final Button buttonCancel = dialogView.findViewById(R.id.noteCancel);

        builder.setView(dialogView).setTitle("Add a new note");

        buttonCancel.setOnClickListener(v -> dismiss());

        buttonAdd.setOnClickListener(v -> {
            Note newNote = new Note(editTitle.getText().toString(),
                    editDescription.getText().toString(),
                    checkBoxImportant.isChecked(),
                    checkBoxTodo.isChecked(),
                    checkBoxIdea.isChecked());
            MainActivity callingActivity = (MainActivity) getActivity();
            Objects.requireNonNull(callingActivity).createNewNoteDialog(newNote);
            dismiss();
        });

        return builder.create();
    }
}
