package com.jaguar.notetoself.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.jaguar.notetoself.MainActivity;
import com.jaguar.notetoself.R;
import com.jaguar.notetoself.note.Note;

import java.util.Objects;

public class DeleteNoteDialog extends DialogFragment {

    Note note;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_delete_note, null);
        builder.setTitle("Delete note");
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setView(dialogView);

        Button buttonYes = dialogView.findViewById(R.id.btn_deleteYes);
        Button buttonCancel = dialogView.findViewById(R.id.btn_deleteCancel);
        buttonYes.setOnClickListener(view -> {
            MainActivity callingActivity = (MainActivity) getActivity();
            Objects.requireNonNull(callingActivity).deleteNote(note);
            dismiss();
        });
        buttonCancel.setOnClickListener(view -> dismiss());

        return builder.create();
    }

    public void sendNoteSelected(Note noteSelected) {
        note = noteSelected;
    }

}
