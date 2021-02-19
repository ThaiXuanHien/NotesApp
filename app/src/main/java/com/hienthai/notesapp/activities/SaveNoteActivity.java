package com.hienthai.notesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hienthai.notesapp.R;
import com.hienthai.notesapp.database.NotesDatabase;
import com.hienthai.notesapp.entities.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SaveNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SaveNoteActivity.class.getName();

    private ImageView imgBack, imgDone;

    private EditText edtNoteTitle, edtNoteSubTitle, edtContentNote;
    private TextView txtDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_note);

        anhXa();

        // format date Friday, 30 june 1998 20:21 PM
        txtDateTime.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date()));


        imgBack.setOnClickListener(this);
        imgDone.setOnClickListener(this);


        Log.e(TAG, "onCreate");
    }

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);
        imgDone = findViewById(R.id.imgSave);
        edtNoteTitle = findViewById(R.id.edtNoteTitle);
        edtNoteSubTitle = findViewById(R.id.edtNoteSubTitle);
        edtContentNote = findViewById(R.id.edtContentNote);
        txtDateTime = findViewById(R.id.txtDateTime);
    }


    private void saveNote() {
        if (edtNoteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note title can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        } else if (edtNoteSubTitle.getText().toString().trim().isEmpty() && edtContentNote.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        }


        final Note note = new Note();
        note.setTitle(edtNoteTitle.getText().toString());
        note.setSubtitle(edtNoteSubTitle.getText().toString());
        note.setNoteText(edtContentNote.getText().toString());
        note.setDateTime(txtDateTime.getText().toString());


        NotesDatabase.getInstance(this).noteDAO().insertNote(note);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.e(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.e(TAG, "onRestart");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgSave:
                saveNote();
                break;
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }
}