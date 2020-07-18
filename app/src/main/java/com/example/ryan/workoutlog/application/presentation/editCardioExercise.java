package com.example.ryan.workoutlog.application.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ryan.workoutlog.application.domain.CardioExercise;
import com.example.ryan.workoutlog.application.domain.Exercise;
import com.example.ryan.workoutlog.R;

import androidx.appcompat.app.AppCompatActivity;

public class editCardioExercise extends AppCompatActivity {
    TextView distance;
    TextView durationText;
    Exercise tempExercise;
    Exercise updatedExercise;
    displayToasts toast = new displayToasts(); //will make toasts for various purposes, takes String message to show user, and getApplicationContext as inputs
    Boolean confirmUpdates = true; //will be set true when user confirms changes and false when an update has been made but not yet saved
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cardio_exercise);
        Bundle data = getIntent().getExtras();
        final Exercise editInProgress = data.getParcelable("value");
        tempExercise = editInProgress;
        updatedExercise = editInProgress;

        setUpViews();
       distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessageAlert("distance");
            }
        });
        //Receive exercise object from sending page


        //Set up initial values to see what user is editing
        if (editInProgress instanceof CardioExercise) {
            CardioExercise tempRes = (CardioExercise) editInProgress;
           // distance.setText(tempRes.getName());

        }
        /*
         * Edit button to edit entire exercise at once
         * Uses EditText's to accept user input then update the exercise in focus
         * */
        Button btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CardioExercise editCardio = (CardioExercise) tempExercise;
                final AlertDialog alertDialog = new AlertDialog.Builder(editCardioExercise.this).create();

                final LinearLayout layout = new LinearLayout(editCardioExercise.this);
                alertDialog.setTitle("Distance x Time");
                layout.setOrientation(LinearLayout.HORIZONTAL);

                final EditText editDistance = new EditText(editCardioExercise.this);
                final EditText editTime = new EditText(editCardioExercise.this);
               // editSets.setText(String.valueOf(editResistance.getSets()));
               // editReps.setText(String.valueOf(editResistance.getReps()));

                editDistance.setHint("Weight");
                editTime.setHint("Duration");
                layout.addView(editDistance);
                layout.addView(editTime);
                alertDialog.setView(layout);

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Submit",

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Editable distanceEditValue = editDistance.getText();
                                editCardio.setDistance(Double.parseDouble(String.valueOf(distanceEditValue)));
                                distance.setText("Edit distance " + editCardio.getDistance());

                                Editable durationEditValue = editTime.getText();
                                editCardio.setDuration(Double.parseDouble(String.valueOf(durationEditValue)));
                                editTime.setText("Edit Duration (Time) ");
                                //editCardio.setTime(Integer.parseInt(String.valueOf(setsEditValue)));
                                //.setText("Edit Sets: " + editResistance.getSets());
/*
                                Editable repsEditValue = editReps.getText();
                                editResistance.setReps(Integer.parseInt(String.valueOf(repsEditValue)));
                                reps.setText("Edit Weight: " + editResistance.getReps());

                               editWeight.invalidate();
                                editSets.invalidate();
                                editReps.invalidate();
                                confirmUpdates = false;
                                dialog.dismiss();

                                toast.makeToast("Updated",getApplicationContext());*/
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
        CardioExercise updateCardio = (CardioExercise) tempExercise;
        distance = findViewById(R.id.textView);
        durationText = findViewById(R.id.textView2);
       distance.setText("Edit Distance "+updateCardio.getDistanceAsKM()+"(KM)");
       durationText.setText("Time "+updateCardio.getDuration()+"(seconds)");
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
    protected void editMessageAlert(String valueToUpdate) {
        final CardioExercise tempRes1 = (CardioExercise) tempExercise;
        final EditText edittext = new EditText(this);
        final AlertDialog alertDialog = new AlertDialog.Builder(editCardioExercise.this).create();
        //final String newValue = valueToUpdate;

        alertDialog.setTitle("Enter New Value");
        //alertDialog.setMessage(valueOf(tempRes1.getWeight()));
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
                        /*switch (newValue){
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
                        }*/
                        confirmUpdates = false;
                        edittext.invalidate();
                        toast.makeToast("Updated",getApplicationContext());

                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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
