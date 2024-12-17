package com.jaguar.notetoself;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ListItemHolder> {

    private final MainActivity nActivity;
    private List<Note> nList;

    public NoteAdapter(List<Note> list, MainActivity activity) {
        nList = list;
        nActivity = activity;
    }

    @NonNull
    @Override
    public NoteAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View noteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        return new ListItemHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ListItemHolder holder, int position) {
        Note note = nList.get(position);
        holder.nTitle.setText(note.getTitle());
        if (note.getDescription().length() > 30) {
            holder.nDescription.setText(String.format("%s...", note.getDescription().substring(0, 30)));
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
