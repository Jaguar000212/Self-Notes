package com.jaguar.selfnotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguar.selfnotes.MainActivity;
import com.jaguar.selfnotes.R;
import com.jaguar.selfnotes.dialogs.ShowNoteDialog;
import com.jaguar.selfnotes.note.Note;

import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ListItemHolder> {

    private final MainActivity nActivity;
    private final List<Note> nList;

    public NotesRecyclerAdapter(List<Note> list, MainActivity activity) {
        nList = list;
        nActivity = activity;
    }

    @NonNull
    @Override
    public NotesRecyclerAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View noteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note, parent, false);
        return new ListItemHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerAdapter.ListItemHolder holder, int position) {
        Note note = nList.get(position);
        if (note.getTitle().length() > 9) {
            holder.nTitle.setText(String.format("%s...", note.getTitle().substring(0, 9)));
        } else {
            holder.nTitle.setText(note.getTitle());
        }
        if (note.getDescription().indexOf("\n") < 24 && note.getDescription().contains("\n")) {
            holder.nDescription.setText(String.format("%s...", note.getDescription().substring(0, note.getDescription().indexOf("\n"))));
        } else if (note.getDescription().length() > 24) {
            holder.nDescription.setText(String.format("%s...", note.getDescription().substring(0, 24)));
        } else holder.nDescription.setText(note.getDescription());
        holder.setNote(note);
    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder {
        private final TextView nTitle;
        private final TextView nDescription;
        private Note note;

        public ListItemHolder(View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.NoteTitle);
            nDescription = itemView.findViewById(R.id.NoteDescription);
            Button nButton = itemView.findViewById(R.id.ShowNote);

            nButton.setOnClickListener(view -> {
                ShowNoteDialog dialog = new ShowNoteDialog();
                dialog.sendNoteSelected(note);
                dialog.show(nActivity.getSupportFragmentManager(), "");
            });
        }

        public void setNote(Note note) {
            this.note = note;
        }
    }
}
