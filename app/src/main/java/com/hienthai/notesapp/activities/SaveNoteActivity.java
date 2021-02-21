package com.hienthai.notesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
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
    private View viewSubTitleIndicator;

    private String selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_note);

        anhXa();

        // format date Friday, 30 june 1998 20:21 PM
        txtDateTime.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date()));


        imgBack.setOnClickListener(this);
        imgDone.setOnClickListener(this);


        initOptionsColor();

        selectedColor = "#333333";

        //setColorSubtitleIndicator();

        Log.e(TAG, "onCreate");
    }

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);
        imgDone = findViewById(R.id.imgSave);
        edtNoteTitle = findViewById(R.id.edtNoteTitle);
        edtNoteSubTitle = findViewById(R.id.edtNoteSubTitle);
        edtContentNote = findViewById(R.id.edtContentNote);
        txtDateTime = findViewById(R.id.txtDateTime);
        viewSubTitleIndicator = findViewById(R.id.viewSubTitleIndicator);
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
        note.setColor(selectedColor);

        NotesDatabase.getInstance(this).noteDAO().insertNote(note);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

    private void initOptionsColor() {
        LinearLayout layoutOptionsColor = findViewById(R.id.layoutOptionsColor);
        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutOptionsColor);
        layoutOptionsColor.findViewById(R.id.txtTitleChooseColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        ImageView imgColor1 = layoutOptionsColor.findViewById(R.id.imgColor1);
        ImageView imgColor2 = layoutOptionsColor.findViewById(R.id.imgColor2);
        ImageView imgColor3 = layoutOptionsColor.findViewById(R.id.imgColor3);
        ImageView imgColor4 = layoutOptionsColor.findViewById(R.id.imgColor4);
        ImageView imgColor5 = layoutOptionsColor.findViewById(R.id.imgColor5);

        layoutOptionsColor.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#333333";
                imgColor1.setImageResource(R.drawable.ic_done);
                imgColor2.setImageResource(0);
                imgColor3.setImageResource(0);
                imgColor4.setImageResource(0);
                imgColor5.setImageResource(0);

                setColorSubtitleIndicator();
            }
        });

        layoutOptionsColor.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#FDBE3B";
                imgColor1.setImageResource(0);
                imgColor2.setImageResource(R.drawable.ic_done);
                imgColor3.setImageResource(0);
                imgColor4.setImageResource(0);
                imgColor5.setImageResource(0);

                setColorSubtitleIndicator();
            }
        });

        layoutOptionsColor.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#FF4842";
                imgColor1.setImageResource(0);
                imgColor2.setImageResource(0);
                imgColor3.setImageResource(R.drawable.ic_done);
                imgColor4.setImageResource(0);
                imgColor5.setImageResource(0);

                setColorSubtitleIndicator();
            }
        });

        layoutOptionsColor.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#3A52fc";
                imgColor1.setImageResource(0);
                imgColor2.setImageResource(0);
                imgColor3.setImageResource(0);
                imgColor4.setImageResource(R.drawable.ic_done);
                imgColor5.setImageResource(0);

                setColorSubtitleIndicator();
            }
        });

        layoutOptionsColor.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#81DD17";
                imgColor1.setImageResource(0);
                imgColor2.setImageResource(0);
                imgColor3.setImageResource(0);
                imgColor4.setImageResource(0);
                imgColor5.setImageResource(R.drawable.ic_done);

                setColorSubtitleIndicator();
            }
        });
    }

    private void setColorSubtitleIndicator(){
        GradientDrawable gradientDrawable = (GradientDrawable) viewSubTitleIndicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedColor));
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