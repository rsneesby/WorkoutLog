package com.example.ryan.workoutlog.Application.Presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.ryan.workoutlog.Application.Logic.ExerciseLoggingLogic;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import com.example.ryan.workoutlog.R;

public class ExerciseLoggingActivity extends AppCompatActivity {
    private ExerciseLoggingLogic exerciseLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logging2);
        this.exerciseLog = new ExerciseLoggingLogic();

        //ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,exerciseLog.getExercises());
    }
}
