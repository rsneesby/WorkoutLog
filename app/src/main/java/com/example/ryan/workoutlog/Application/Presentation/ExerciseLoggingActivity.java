package com.example.ryan.workoutlog.Application.Presentation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.Application.Logic.ExerciseLoggingLogic;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import com.example.ryan.workoutlog.R;

import java.io.Serializable;
import java.util.List;

public class ExerciseLoggingActivity extends Activity implements Serializable {

    private LoggedExerciesPersistanceStub stub = new LoggedExerciesPersistanceStub();
    private ExerciseLoggingLogic exerciseLog = new ExerciseLoggingLogic(stub);
    String loggedExercises[];
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logging2);
        setUpViews();

    }

    protected void setUpViews() {
        loggedExercises = listToStringArray();
        listView = (ListView) findViewById(R.id.test);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.logged_exercise_listview, R.id.exerciseList, loggedExercises);
        listView.setAdapter(adapter);
        //hold click for alertdialog to pop asking user if they want to delete the item
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long id) {

               // final Exercise inFocus = exerciseLog.getIndex(position);
                AlertDialog.Builder backConfirmation = new AlertDialog.Builder(ExerciseLoggingActivity.this);
                //backConfirmation.setTitle("Delete Exercise?");
                backConfirmation.setPositiveButton("Delete Exercise?", new DialogInterface.OnClickListener() {
                    //Returns without saving edited data
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        exerciseLog.deleteExercise(exerciseLog.getIndex(position));
                        setUpViews();
                    }
                });
                backConfirmation.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            backConfirmation.show();
                return true;
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (exerciseLog.getIndex(position) instanceof ResistanceExercise) {
                    Intent i = new Intent(ExerciseLoggingActivity.this, editResistanceExercise.class);
                    i.putExtra("value", exerciseLog.getIndex(position));
                    startActivityForResult(i, 1);
                } else if (exerciseLog.getIndex(position) instanceof CardioExercise) {
                    Intent i = new Intent(ExerciseLoggingActivity.this, editResistanceExercise.class);
                    i.putExtra("value", exerciseLog.getIndex(position));
                    startActivity(i);
                }
            }
        });
    }

    /*Receives edited Exercise from the editResistanceExercise.java activity when user hits the back button and will update the exercise in the (stub) DB
     * Uses onResume() to update pageview
     * */
    //TODO better way to refresh data than calling setUpViews again? using onResume?
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Bundle editData = data.getExtras();
                Exercise editedExerciseResult = editData.getParcelable("updatedExercise");

                stub.updateExercise(editedExerciseResult);

            }
        }

    }

    @Override
    protected void onResume() {
        setUpViews();
        super.onResume();
    }

    private String[] listToStringArray() {
        String[] a = new String[stub.getSize()];
        List<Exercise> exercises = stub.getExercises();
        for (int i = 0; i < stub.getSize(); i++) {
            if (exercises.get(i) instanceof CardioExercise) {
                a[i] = exercises.get(i).toString();
            } else {
                a[i] = exercises.get(i).toString();
            }
        }
        return a;
    }
}
