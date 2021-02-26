package com.hienthai.notesapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.notesapp.R;
import com.hienthai.notesapp.entities.Note;
import com.hienthai.notesapp.listeners.NotesListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private NotesListener notesListener;

    public NotesAdapter(List<Note> noteList, NotesListener notesListener) {
        this.noteList = noteList;
        this.notesListener = notesListener;
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
}
