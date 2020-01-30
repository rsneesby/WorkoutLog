package com.example.ryan.workoutlog.Application.Domain;

import java.util.Date;

public class CardioExercise extends Exercise {
    private static final double SECONDS_PER_HOUR = 3600;
    public double distance;

    public CardioExercise(int id, Date dayCompleted, double duration, String name, String comment, double distance){
        super(id, dayCompleted, duration, name, comment);
        this.distance = distance;
    }

    public CardioExercise(Date dayCompleted, double duration, String name, String comment, double distance){
        this(0, dayCompleted, duration, name, comment, distance);
    }

    public CardioExercise(){
        this(0, new Date(), 0, null, null, 0);
    }

    //Returns the average speed the exercise was completed at in km/h
    public double getAverageSpeed(){
        double totalHours = duration/SECONDS_PER_HOUR;
        double totalKilometers = distance/1000;

        if(totalHours == 0){
            return 0;
        }

        return totalKilometers/totalHours;
    }
    public double getDistance(){
        return this.distance;
    }
    public void setDistance(double distance){
        this.distance = distance;
    }
}
