package com.example.ryan.workoutlog.Application.Persistance;
import com.example.ryan.workoutlog.Application.Domain.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LoggedExerciesPersistanceStub {
    private ArrayList<Exercise> Exercises; //private to avoid outside editing

    // Takes in any list of exercises
    public LoggedExerciesPersistanceStub(ArrayList<Exercise> theList) {
        this.Exercises = theList;
    }

    // Creates an empty list if none provided
    public LoggedExerciesPersistanceStub() {
        this.Exercises = new ArrayList<>();
        // 10km run sample
        Exercises.add(new CardioExercise( new Date(2018, 7, 20),
                60*60,
                "10km Run",
                "First attempt at 10km run",
                10000));

        Exercises.add(new CardioExercise( new Date(2018, 7, 30),
                3,
                "Sprints",
                "First attempt at our sprint. That was easy! :)",
                20));


        Exercises.add(new ResistanceExercise( new Date(2018, 7, 20),
                1,
                "Squats",
                "Learning to Squat",
                300,
                10));
        Exercises.add(new ResistanceExercise( new Date(2018, 7, 25),
                1,
                "Squats",
                "Learning to Squat",
                300,
                20));
        Exercises.add(new ResistanceExercise( new Date(2018, 7, 27),
                2,
                "Squats",
                "Learning to Squat",
                100,
                5));

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

    // Update the exercise
    public boolean updateExercise(Exercise revisedExercise) {
        int index = Exercises.indexOf(revisedExercise);
        if (index >= 0)
        {
            Exercises.set(index, revisedExercise);
            return true;
        }
        return false;

    }

    // Delete our exercise from the list
    public boolean deleteExercise(Exercise revisedExercise) {
        int index = Exercises.indexOf(revisedExercise);
        if (index >= 0)
        {
            Exercises.remove(index);
            return true;
        }
        return false;

    }

}
