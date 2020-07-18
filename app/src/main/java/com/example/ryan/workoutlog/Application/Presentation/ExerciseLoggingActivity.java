package com.example.ryan.workoutlog.Application.Presentation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.Application.Logic.ExerciseLoggingLogic;
import com.example.ryan.workoutlog.Application.MainActivity;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import com.example.ryan.workoutlog.R;

import java.io.Serializable;
import java.util.List;

public class ExerciseLoggingActivity extends Activity implements Serializable {

    private LoggedExerciesPersistanceStub stub = new LoggedExerciesPersistanceStub();
    private ExerciseLoggingLogic exerciseLog = new ExerciseLoggingLogic(stub);
    private displayToasts toastBuilder = new displayToasts();
    private BottomNavigationView mMainNav;
    String loggedExercises[];
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logging);
        setUpViews();

    }

    protected void setUpViews() {

        mMainNav =(BottomNavigationView) findViewById(R.id.main_nav);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.loggedExerciseNav:
                        //Intent intent = new Intent(MainActivity.this, ExerciseLoggingActivity.class);

                        //startActivity(intent);
                        break;
                    case R.id.home_nav:
                        Intent intent = new Intent(ExerciseLoggingActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.profileMenu:
                        Intent profile = new Intent(ExerciseLoggingActivity.this, Profile.class);
                        startActivity(profile);
                }
                return false;
            }
        });


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
                        toastBuilder.makeToast("Deleted",ExerciseLoggingActivity.this);
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
                    Intent i = new Intent(ExerciseLoggingActivity.this, editCardioExercise.class);
                    i.putExtra("value", exerciseLog.getIndex(position));
                    startActivityForResult(i,1);
                }
            }
        });
    }

    /*Receives edited Exercise from the editResistanceExercise.java activity when user hits the back button and will update the exercise in the (stub) DB
     * Uses onResume() to update pageview
     * */
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
    public void newExercise(View view){
        //TODO add ability to also select values from a drop down in addition to entering custom ones, can this be done with 1 object?
        //also could provide user with general list, then allow them to add custom exercises to that drop down list for the future
        /*int id, double duration, String name, String comment, double weight, int reps,int sets){
        super(id, duration, name, comment);
        Ask user for: name(selectable from drop down menu? or string/menu option), weight, reps, sets
        */
        final AlertDialog.Builder addExercise = new AlertDialog.Builder(ExerciseLoggingActivity.this);
        final EditText exerciseName = new EditText(this);
        final EditText exerciseWeight = new EditText(this);
        final EditText exerciseReps = new EditText(this);
        final EditText exerciseSets = new EditText(this);

        final Editable userInputName = exerciseName.getText();
        final Editable userInputWeight = exerciseWeight.getText();
        final Editable userInputSets = exerciseSets.getText();
        final Editable userInputReps = exerciseReps.getText();


        exerciseName.setHint("Name");
        exerciseWeight.setHint("Weight");
        exerciseReps.setHint("Reps");
        exerciseSets.setHint("Sets");
        final LinearLayout layout = new LinearLayout(ExerciseLoggingActivity.this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        layout.addView(exerciseName);
        layout.addView(exerciseWeight);
        layout.addView(exerciseSets);
        layout.addView(exerciseReps);


        addExercise.setView(layout);

        addExercise.setPositiveButton("Add Exercise", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                exerciseLog.addExercise(new ResistanceExercise(1337,0,userInputName.toString(),"",Double.parseDouble(String.valueOf(userInputWeight)),
                        Integer.parseInt(String.valueOf(userInputReps)),Integer.parseInt(String.valueOf(userInputSets))));
                toastBuilder.makeToast("Added",ExerciseLoggingActivity.this);
                setUpViews();
            }
        });
        addExercise.show();
    }
    @Override
    protected void onResume() {
        setUpViews();
        super.onResume();
    }
/*Used to setup ListView with exercises
*
* */
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
