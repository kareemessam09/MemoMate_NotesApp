package com.example.notesapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesapp.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText edittext_title,edittext_content;
    ImageView saveicon;
    Notes notes;
    boolean isOld = false;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        edittext_title = findViewById(R.id.edittext_title);
        edittext_content = findViewById(R.id.edittext_content);

        saveicon = findViewById(R.id.saveicon);

        notes = new Notes();


        button = findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            notes = (Notes) getIntent().getSerializableExtra("old note");
            edittext_title.setText(notes.getTitle());
            edittext_content.setText(notes.getNotes());
            isOld = true;
        }catch (Exception e){
            e.printStackTrace();
        }




        saveicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edittext_title.getText().toString();
                String desc = edittext_content.getText().toString();
                if (desc.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this,"Please add some notes!",Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date = new Date();
                if (!isOld){
                    notes = new Notes();
                }
                notes.setTitle(title);
                notes.setNotes(desc);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });


    }


}