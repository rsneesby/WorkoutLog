package com.example.ryan.workoutlog.Application.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Logic.ExerciseLoggingLogic;
import com.example.ryan.workoutlog.Application.MainActivity;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import com.example.ryan.workoutlog.R;

public class ExerciseLoggingActivity extends AppCompatActivity {
    private ExerciseLoggingLogic exerciseLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logging2);

        Intent intent = getIntent();
        LoggedExerciesPersistanceStub stub = intent.getParcelableExtra("Stub");


        this.exerciseLog = new ExerciseLoggingLogic(stub);
        //TODO how to display this? adapter for custom object, or add toString for proper display to logging logic class?
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_exercise_logging2,stub);
        //adapter.add("testing");
    }
}
