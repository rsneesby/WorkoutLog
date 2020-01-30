package com.example.ryan.workoutlog.Application.Domain;

import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import java.util.Date;
public class ResistanceExercise extends Exercise {
    public double weight;
    public int reps;


    public ResistanceExercise(int id, Date dayCompleted, double duration, String name, String comment, double weight, int reps){
        super(id, dayCompleted, duration, name, comment);
        this.weight = weight;
        this.reps = reps;

    }

    public ResistanceExercise(Date dayCompleted, double duration, String name, String comment, double weight, int reps){
        this(0, dayCompleted, duration, name, comment, weight, reps);
    }

    public ResistanceExercise(){
        this(0, new Date(), 0, null, null, 0, 0);
    }
    public void setWeight(Double weight){
        this.weight = weight;
    }
    public void setReps(int reps){
        this.reps = reps;
    }
}
