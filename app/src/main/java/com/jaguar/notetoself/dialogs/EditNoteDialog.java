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

public class EditNoteDialog extends DialogFragment {

    Note note;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_note, null);

        final EditText editTitle = dialogView.findViewById(R.id.noteTitle);
        final EditText editDescription = dialogView.findViewById(R.id.noteDescription);
        final CheckBox checkBoxIdea = dialogView.findViewById(R.id.noteIdea);
        final CheckBox checkBoxTodo = dialogView.findViewById(R.id.noteToDo);
        final CheckBox checkBoxImportant = dialogView.findViewById(R.id.noteImportant);
        final Button buttonAdd = dialogView.findViewById(R.id.noteSave);
        final Button buttonCancel = dialogView.findViewById(R.id.noteCancel);

        editTitle.setText(note.getTitle());
        editDescription.setText(note.getDescription());
        checkBoxIdea.setChecked(note.isIdea());
        checkBoxTodo.setChecked(note.isTodo());
        checkBoxImportant.setChecked(note.isImportant());

        builder.setView(dialogView).setTitle(R.string.edit_text);

        buttonAdd.setOnClickListener(v -> {
            note.setTitle(editTitle.getText().toString());
            note.setDescription(editDescription.getText().toString());
            note.setIdea(checkBoxIdea.isChecked());
            note.setTodo(checkBoxTodo.isChecked());
            note.setImportant(checkBoxImportant.isChecked());

            MainActivity callingActivity = (MainActivity) getActivity();
            Objects.requireNonNull(callingActivity).editNote();
            dismiss();
        });

        buttonCancel.setOnClickListener(v -> dismiss());

        return builder.create();
    }

    public void sendNoteSelected(Note noteSelected) {
        note = noteSelected;
    }

}
