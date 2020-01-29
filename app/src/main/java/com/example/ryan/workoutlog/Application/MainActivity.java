package com.example.ryan.workoutlog.Application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ryan.workoutlog.Application.Presentation.DisplayMessageActivity;
import com.example.ryan.workoutlog.Application.Presentation.ExerciseLoggingActivity;
import com.example.ryan.workoutlog.R;

public class MainActivity extends AppCompatActivity {

    public static void main(String[] args)
    {

    }
    public static final String EXTRA_MESSAGE = "com.example.ryan.workoutlog.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
    public void openExerciseLogging(View view){
        Intent intent = new Intent(this, ExerciseLoggingActivity.class);
        Button loggingButton = (Button) findViewById(R.id.exerciseLogging);
        startActivity(intent);
    }
    private void setupViews(){

       // EditText test = findViewById(R.id.editText);

    }
}
