package com.example.ryan.workoutlog.application.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ryan.workoutlog.application.MainActivity;
import com.example.ryan.workoutlog.R;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        //receive data from main activity and set the pages textview as that string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
    }
}
