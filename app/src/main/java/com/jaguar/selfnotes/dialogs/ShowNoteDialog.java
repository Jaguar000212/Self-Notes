package com.jaguar.selfnotes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.jaguar.selfnotes.R;
import com.jaguar.selfnotes.note.Note;

public class ShowNoteDialog extends DialogFragment {
    private Note note;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

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

        Button buttonEdit = dialogView.findViewById(R.id.btn_Edit);
        buttonEdit.setOnClickListener(v -> {
            EditNoteDialog editNoteDialog = new EditNoteDialog();
            editNoteDialog.sendNoteSelected(note);
            editNoteDialog.show(getParentFragmentManager(), "EditNoteDialog");
            dismiss();
        });

        Button buttonOK = dialogView.findViewById(R.id.btn_OK);
        buttonOK.setOnClickListener(v -> dismiss());

        Button buttonDelete = dialogView.findViewById(R.id.btn_Delete);
        buttonDelete.setOnClickListener(v -> {
            DeleteNoteDialog deleteNoteDialog = new DeleteNoteDialog();
            deleteNoteDialog.sendNoteSelected(note);
            deleteNoteDialog.show(getParentFragmentManager(), "DeleteNoteDialog");
            dismiss();
        });

        Button buttonShare = dialogView.findViewById(R.id.btn_Share);
        buttonShare.setOnClickListener(v -> {
            StringBuilder tags = new StringBuilder();
            if (note.isImportant()) {
                tags.append("[Important] ");
            }
            if (note.isTodo()) {
                tags.append("[To-Do] ");
            }
            if (note.isIdea()) {
                tags.append("[Idea] ");
            }
            String shareText = "Title: " + note.getTitle() +
                    "\nDescription:\n" + note.getDescription() +
                    "\n\nTags: " + tags;
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        builder.setView(dialogView).setTitle(note.getTitle());
        return builder.create();
    }

    public void sendNoteSelected(Note noteSelected) {
        note = noteSelected;
    }
}
