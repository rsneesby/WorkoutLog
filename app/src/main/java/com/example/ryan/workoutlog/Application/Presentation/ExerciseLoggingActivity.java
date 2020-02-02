package com.example.ryan.workoutlog.Application.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.Application.Logic.ExerciseLoggingLogic;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import com.example.ryan.workoutlog.R;

import java.io.Serializable;
import java.util.List;

public class ExerciseLoggingActivity extends Activity implements Serializable {
    private ExerciseLoggingLogic exerciseLog;
    private LoggedExerciesPersistanceStub stub = new LoggedExerciesPersistanceStub();
    String test[] = {"Squat","Bench","Deadlift"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logging2);

       //Intent intent = getIntent();

       // this.stub = (LoggedExerciesPersistanceStub)intent.getSerializableExtra("intent");


        this.exerciseLog = new ExerciseLoggingLogic(stub);
        //TODO make exercises parcelable in order to allow edits

       test = listToStringArray();
        ListView listView = (ListView) findViewById(R.id.test);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.logged_exercise_listview,R.id.exerciseList, test);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent i = new Intent(ExerciseLoggingActivity.this,editExercise.class);
                   if(exerciseLog.getIndex(position) instanceof ResistanceExercise){
                       i.putExtra("value",exerciseLog.getIndex(position));
                       startActivity(i);
                   }

            }
        });

}
    private String[] listToStringArray()
    {
        String[] a= new String[stub.getSize()];

       List<Exercise> exercises = stub.getExercises();
        //exercises.size();
        for (int i = 0; i <stub.getSize() ; i++) {
           // temp[i] = exercises.get(i).toString();
            if (exercises.get(i) instanceof CardioExercise) {
                a[i] = exercises.get(i).toString();
            }
            else {
                 a[i] = exercises.get(i).toString();
            }
        }
        return a;
    }
}
