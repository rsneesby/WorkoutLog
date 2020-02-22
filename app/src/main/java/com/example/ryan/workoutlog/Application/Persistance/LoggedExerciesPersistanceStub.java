package com.example.ryan.workoutlog.Application.Persistance;
import com.example.ryan.workoutlog.Application.Domain.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class LoggedExerciesPersistanceStub implements Serializable {
    private ArrayList<Exercise> Exercises; //private to avoid outside editing

    // Takes in any list of exercises
    public LoggedExerciesPersistanceStub(ArrayList<Exercise> theList) {
        this.Exercises = theList;
    }


    // Creates an empty list if none provided
    public LoggedExerciesPersistanceStub() {

        this.Exercises = new ArrayList<>();
        // 10km run sample
        //double duration, String name, String comment, double distance
        Exercises.add(new CardioExercise(
                9,
                60,
                "10km Run",
                "First attempt at 10km run",
                10000));

        Exercises.add(new CardioExercise(
                7,
                3,
                "Sprints",
                "First attempt at our sprint. That was easy! :)",
                20));


        Exercises.add(new ResistanceExercise(0,
                1,
                "Squats",
                "Learning to Squat",
                300,
                10,3));
        Exercises.add(new ResistanceExercise(1,
                1,
                "Squats",
                "Learning to Squat",
                300,
                20,3));
        Exercises.add(new ResistanceExercise(2,
                2,
                "Squats",
                "Learning to Squat",
                100,
                5,3));

    }
    //size
    public int getSize(){
        return this.Exercises.size();
    }
    // Get a list of all exercises
    public List<Exercise> getExercises() {
        //give the caller a view only list
        return Collections.unmodifiableList(this.Exercises);

    }

    // Add Exercise to list
    public boolean insertExercise(Exercise newExercise) {
        return Exercises.add(newExercise);
    }
    public Exercise getPosition(int position){
        return Exercises.get(position);
    }
    // Update the exercise
    public boolean updateExercise(Exercise revisedExercise) {
        for (int i = 0; i <Exercises.size() ; i++) {
            if(Exercises.get(i).getId() == revisedExercise.getId()){
                Exercises.set(i,revisedExercise);

                break;
            }
        }
        return false;

    }

    // Delete our exercise from the list
    public boolean deleteExercise(Exercise revisedExercise) {
        for (int i = 0; i <Exercises.size() ; i++) {
            if(Exercises.get(i).getId() == revisedExercise.getId()){
                Exercises.remove(i);
            }
        }

        return false;

    }

}
