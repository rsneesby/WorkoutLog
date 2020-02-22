package com.example.ryan.workoutlog.Application.Presentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.Application.MainActivity;
import com.example.ryan.workoutlog.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class editResistanceExercise extends AppCompatActivity {
    TextView temp;
    TextView weight;
    TextView sets;
    TextView reps;
    Exercise tempExercise;
    Exercise updatedExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resistance_exercise);

       // setUpViews();
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
                //alertDialog.setMessage(valueOf(tempRes1.getWeight()));
                layout.setOrientation(LinearLayout.HORIZONTAL);

                final EditText editWeight = new EditText(editResistanceExercise.this);
                final EditText editSets = new EditText(editResistanceExercise.this);
                final EditText editReps = new EditText(editResistanceExercise.this);
               // editWeight.setText(String.valueOf(editResistance.getWeight()));
                editSets.setText(String.valueOf(editResistance.getSets()));
                editReps.setText(String.valueOf(editResistance.getReps()));

                editWeight.setHint("Weight");
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
                                dialog.dismiss();
                                //Toast to notify user their change was made
                                Context context = getApplicationContext();
                                CharSequence text = "Updated";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context,text,duration);
                                toast.show();
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

    }
/*
* Method to allow for editing and updating of single values
* Will popup an AlertDialog when an individual component of the exercise is clicked, represented by a TextView
* TODO add a cancel button,
* */
    protected Exercise editMessageAlert(String valueToUpdate) {

        final ResistanceExercise tempRes1 = (ResistanceExercise) tempExercise;
        final EditText edittext = new EditText(this);
        final AlertDialog alertDialog = new AlertDialog.Builder(editResistanceExercise.this).create();
        final String newValue = valueToUpdate;

        alertDialog.setTitle("Enter New Value");
        alertDialog.setMessage(valueOf(tempRes1.getWeight()));
        alertDialog.setView(edittext);
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

                        edittext.invalidate();

                        //Toast to notify user their change was made
                        Context context = getApplicationContext();
                        CharSequence text = "Updated";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context,text,duration);
                        toast.show();
                        dialog.dismiss();

                    }
                });
        alertDialog.show();
        return tempExercise;
    }
//when user returns from editing page, value is sent to previous page to update list
    //TODO add a confirmation to ensure user wants to actually update or return to previous page without saving changes
    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("updatedExercise",updatedExercise);
        setResult(Activity.RESULT_OK,resultIntent);
        finish();
    }


}
