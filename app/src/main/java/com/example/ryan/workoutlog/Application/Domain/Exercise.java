package com.example.ryan.workoutlog.Application.Domain;

import com.example.ryan.workoutlog.Application.Domain.DomainInterfaces.ExerciseInterface;

import java.util.Date;

public class Exercise implements ExerciseInterface{
    public Date dayCompleted;
    public double duration;
    public String name;
    public String comment;

    private int id;

    //private final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public Exercise(int newId, Date dayCompleted, double newDuration, String name, String comment){
        this.id = newId;
        this.dayCompleted = dayCompleted;
        this.duration = newDuration;
        this.name = name;
        this.comment = comment;
    }

    public Exercise(Date dayCompleted, double newDuration, String name, String comment){
        this(0, dayCompleted, newDuration, name, comment);
    }


    public Date getDate(){
        return this.dayCompleted;
    }
    public String getExerciseName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public Exercise(){
        this(0, new Date(), 0, null, null);
    }
}
