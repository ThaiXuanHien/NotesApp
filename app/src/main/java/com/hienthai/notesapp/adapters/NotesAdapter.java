package com.hienthai.notesapp.adapters;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.notesapp.R;
import com.hienthai.notesapp.entities.Note;
import com.hienthai.notesapp.listeners.NotesListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private NotesListener notesListener;

    private Timer timer;
    private List<Note> noteSource;

    public NotesAdapter(List<Note> noteList, NotesListener notesListener) {
        this.noteList = noteList;
        this.notesListener = notesListener;

        noteSource = noteList;
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


        if (note.getColor() != null) {
            holder.layoutItemNote.setBackgroundColor(Color.parseColor(note.getColor()));
        } else {
            holder.layoutItemNote.setBackgroundColor(Color.parseColor("#333333"));
        }


        if (note.getImagePath() != null) {
            holder.imgItemNote.setImageBitmap(BitmapFactory.decodeFile(note.getImagePath()));


            holder.imgItemNote.setVisibility(View.VISIBLE);
        } else {
            holder.imgItemNote.setVisibility(View.GONE);
        }


        holder.layoutItemNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesListener.onNoteClicked(note, position);
            }
        });
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

        LinearLayout layoutItemNote;

        RoundedImageView imgItemNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitleItemNote = itemView.findViewById(R.id.txtTitleItemNote);
            txtSubtitleItemNote = itemView.findViewById(R.id.txtSubtitleItemNote);
            txtDateTimeItemNote = itemView.findViewById(R.id.txtDateTimeItemNote);
            layoutItemNote = itemView.findViewById(R.id.layoutItemNote);
            imgItemNote = itemView.findViewById(R.id.imgItemNote);
        }
    }

    public void searchNote(final String keyword) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (keyword.trim().isEmpty()) {
                    noteList = noteSource;
                } else {
                    List<Note> temp = new ArrayList<>();
                    for (Note note : noteSource) {
                        if (note.getTitle().trim().toLowerCase().contains(keyword.toLowerCase())
                                || note.getSubtitle().trim().toLowerCase().contains(keyword.toLowerCase())
                                || note.getNoteText().trim().toLowerCase().contains(keyword.toLowerCase())) {
                            temp.add(note);
                        }
                    }
                    noteList = temp;
                }

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });

            }
        }, 500);
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
