package com.example.ryan.workoutlog.Application.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class CardioExercise extends Exercise implements Parcelable{
    private static final double SECONDS_PER_HOUR = 3600;
    public double distance=0;
    public static final Parcelable.Creator<CardioExercise> CREATOR = new Parcelable.Creator<CardioExercise>(){
        public CardioExercise createFromParcel(Parcel in){

            return new CardioExercise(in);
        }
        public CardioExercise[] newArray(int size){
            return new CardioExercise[size];
        }
    };
    private CardioExercise(Parcel in){
        super(in);
        distance = in.readDouble();

    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest,flags);
        dest.writeDouble(this.distance);

    }
    @Override
    public int describeContents(){
        return 0;
    }
    public CardioExercise(int id, double duration, String name, String comment, double distance){
        super(id, duration, name, comment);
        this.distance = distance;
    }


   // public CardioExercise(int i, String sprints, String s, int i1){
        //this(0, 0, null, null, 0);
    //}

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

    public double getDistanceAsKM(){
        return this.distance/1000;
    }
    public String toString(){
        String temp="Name: "+name+" Duration: "+duration+"(km)";

        return temp;
    }


}
