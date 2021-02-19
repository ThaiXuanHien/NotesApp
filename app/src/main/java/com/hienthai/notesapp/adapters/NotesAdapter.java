package com.hienthai.notesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.notesapp.R;
import com.hienthai.notesapp.entities.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> noteList;

    public NotesAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }


    public void setData(List<Note> noteList) {
        this.noteList = noteList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);

        if (note == null) {
            return;
        }

        holder.txtTitleItemNote.setText(note.getTitle());
        holder.txtSubtitleItemNote.setText(note.getSubtitle());
        holder.txtDateTimeItemNote.setText(note.getDateTime());
    }

    @Override
    public int getItemCount() {
        if (noteList != null)
            return noteList.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitleItemNote, txtSubtitleItemNote, txtDateTimeItemNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitleItemNote = itemView.findViewById(R.id.txtTitleItemNote);
            txtSubtitleItemNote = itemView.findViewById(R.id.txtSubtitleItemNote);
            txtDateTimeItemNote = itemView.findViewById(R.id.txtDateTimeItemNote);

        }
    }
}
