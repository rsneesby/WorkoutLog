package com.example.ryan.workoutlog.Application.Presentation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.String.valueOf;

public class editResistanceExercise extends AppCompatActivity {
    TextView temp;
    TextView weight;
    TextView sets;
    TextView reps;
    Exercise tempExercise;
    Exercise updatedExercise;
    displayToasts toast = new displayToasts(); //will make toasts for various purposes, takes String message to show user, and getApplicationContext as inputs
    Boolean confirmUpdates = true; //will be set true when user confirms changes and false when an update has been made but not yet saved
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resistance_exercise);

       setUpViews();
        temp = findViewById(R.id.textView);
        weight = findViewById(R.id.textView3);
        sets = findViewById(R.id.textView4);
        reps = findViewById(R.id.textView5);

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessageAlert("weight");
            }
        });
        sets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessageAlert("sets");
            }
        });
        reps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessageAlert("reps");
            }
        });
        //Receive exercise object from sending page
        Bundle data = getIntent().getExtras();
        final Exercise editInProgress = data.getParcelable("value");
        tempExercise = editInProgress;
        updatedExercise = editInProgress;
        //Set up initial values to see what user is editing
        if (editInProgress instanceof ResistanceExercise) {
            ResistanceExercise tempRes = (ResistanceExercise) editInProgress;
            temp.setText(tempRes.getName());

            weight.setText("Edit Weight: " + tempRes.getWeight());
            sets.setText("Edit Sets: " + tempRes.getSets());
            reps.setText("Edit Reps: " + tempRes.getReps());
        } else if (editInProgress instanceof CardioExercise) {
            CardioExercise tempCardio = (CardioExercise) editInProgress;
            temp.setText(tempCardio.toString());
        }
 /*
 * Edit button to edit entire exercise at once
 * Uses EditText's to accept user input then update the exercise in focus
 * */
        Button btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ResistanceExercise editResistance = (ResistanceExercise) tempExercise;
                final AlertDialog alertDialog = new AlertDialog.Builder(editResistanceExercise.this).create();

                final LinearLayout layout = new LinearLayout(editResistanceExercise.this);
                alertDialog.setTitle("Weight x Sets x Reps");
                layout.setOrientation(LinearLayout.HORIZONTAL);

                final EditText editWeight = new EditText(editResistanceExercise.this);
                final EditText editSets = new EditText(editResistanceExercise.this);
                final EditText editReps = new EditText(editResistanceExercise.this);
                editSets.setText(String.valueOf(editResistance.getSets()));
                editReps.setText(String.valueOf(editResistance.getReps()));

                editWeight.setHint("Weight");
                editSets.setHint("Sets");
                editReps.setHint("Reps");
                layout.addView(editWeight);
                layout.addView(editSets);
                layout.addView(editReps);
                alertDialog.setView(layout);

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Submit",

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Editable weightEditValue = editWeight.getText();
                                editResistance.setWeight(Double.parseDouble(String.valueOf(weightEditValue)));
                                weight.setText("Edit Weight: " + editResistance.getWeight());

                                Editable setsEditValue = editSets.getText();
                                editResistance.setSets(Integer.parseInt(String.valueOf(setsEditValue)));
                                sets.setText("Edit Sets: " + editResistance.getSets());

                                Editable repsEditValue = editReps.getText();
                                editResistance.setReps(Integer.parseInt(String.valueOf(repsEditValue)));
                                reps.setText("Edit Weight: " + editResistance.getReps());

                                editWeight.invalidate();
                                editSets.invalidate();
                                editReps.invalidate();
                                confirmUpdates = false;
                                dialog.dismiss();

                                toast.makeToast("Updated",getApplicationContext());
                            }
                        });
                alertDialog.show();
            }
        });
    }
/*Set up page on creation
Click listeners are for individual exercise components so they can be edited individually if necessary
* */
    public void setUpViews() {
        Button btn_saveEdit = findViewById(R.id.btn_saveEdit);
        btn_saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmUpdates = true;
                toast.makeToast("Changes Saved",getApplicationContext());
            }
        });
    }
/*
* Method to allow for editing and updating of single values
* Will popup an AlertDialog when an individual component of the exercise is clicked, represented by a TextView
*
* */
    protected Exercise editMessageAlert(String valueToUpdate) {
        final ResistanceExercise tempRes1 = (ResistanceExercise) tempExercise;
        final EditText edittext = new EditText(this);
        final AlertDialog alertDialog = new AlertDialog.Builder(editResistanceExercise.this).create();
        final String newValue = valueToUpdate;

        alertDialog.setTitle("Enter New Value");
        alertDialog.setMessage(valueOf(tempRes1.getWeight()));
        alertDialog.setView(edittext);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Submit",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Editable userInput = edittext.getText();
                        switch (newValue){
                            case "weight":
                                tempRes1.setWeight(Double.parseDouble(String.valueOf(userInput)));
                                weight.setText("Edit Weight: " + tempRes1.getWeight());
                                break;
                            case "sets":
                                tempRes1.setSets(Integer.parseInt(String.valueOf(userInput)));
                                sets.setText("Edit Sets: " + tempRes1.getSets());
                                break;
                            case "reps":
                                tempRes1.setReps(Integer.parseInt(String.valueOf(userInput)));
                                reps.setText("Edit Reps: " + tempRes1.getReps());
                                break;
                        }
                        confirmUpdates = false;
                        edittext.invalidate();
                        toast.makeToast("Updated",getApplicationContext());

                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        return tempExercise;
    }
//when user returns from editing page, value is sent to previous page to update list
    //TODO add a confirmation to ensure user wants to actually update or return to previous page without saving changes
    //use the confirmation to change confirmation boolean to true, also when changes are saved, should it automatically go to previous page or wait for user to manually return
    @Override
    public void onBackPressed() {
        String positiveButton = "Yes";
       if(!confirmUpdates) {
           AlertDialog.Builder backConfirmation = new AlertDialog.Builder(this);
           backConfirmation.setTitle("Continue without saving updates?");
           backConfirmation.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
              //Returns without saving edited data
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {

                   finish();
               }
           });
           backConfirmation.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                    //remain on current page
               }
           });
           backConfirmation.setNeutralButton("Save", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   Intent resultIntent = new Intent();
                   resultIntent.putExtra("updatedExercise", updatedExercise);
                   setResult(Activity.RESULT_OK, resultIntent);
                   finish();
               }
           });
           backConfirmation.show();
       }
       else{
           Intent resultIntent = new Intent();
           resultIntent.putExtra("updatedExercise", updatedExercise);
           setResult(AppCompatActivity.RESULT_OK, resultIntent);
           finish();
       }
    }
}