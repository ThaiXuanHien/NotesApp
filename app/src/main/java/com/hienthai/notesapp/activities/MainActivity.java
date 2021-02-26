package com.hienthai.notesapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hienthai.notesapp.R;
import com.hienthai.notesapp.adapters.NotesAdapter;
import com.hienthai.notesapp.database.NotesDatabase;
import com.hienthai.notesapp.entities.Note;
import com.hienthai.notesapp.listeners.NotesListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesListener {

    private static final String TAG = MainActivity.class.getName();

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE = 2;

    private ImageView imgAddNoteMain;
    private RecyclerView rcvNotes;

    private List<Note> noteList;
    private NotesAdapter notesAdapter;


    private int noteClickedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        imgAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, SaveNoteActivity.class), REQUEST_CODE_ADD_NOTE);
            }
        });


        loadDataNotes();


        Log.e(TAG, "onCreate");

    }

    private void loadDataNotes() {
        noteList = NotesDatabase.getInstance(this).noteDAO().getAllNotes();
        notesAdapter.setData(noteList);

    }

    private void anhXa() {
        imgAddNoteMain = findViewById(R.id.imgAddNoteMain);
        rcvNotes = findViewById(R.id.rcvNotes);
        rcvNotes.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        noteList = new ArrayList<>();

        notesAdapter = new NotesAdapter(noteList, this);

        rcvNotes.setAdapter(notesAdapter);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK && data != null) {
            loadDataNotes();
        } else if (requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK && data != null) {
            loadDataNotes();
        }
    }

    @Override
    public void onNoteClicked(Note note, int position) {

        noteClickedPosition = position;

        Intent intent = new Intent(this, SaveNoteActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);

    }
}