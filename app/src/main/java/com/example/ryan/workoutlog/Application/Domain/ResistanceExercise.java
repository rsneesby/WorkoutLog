package com.example.ryan.workoutlog.Application.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import java.util.Date;
public class ResistanceExercise extends Exercise implements Parcelable {
    private double weight;
    private int reps;
    private int sets;

    public static final Parcelable.Creator<ResistanceExercise> CREATOR = new Parcelable.Creator<ResistanceExercise>(){
        public ResistanceExercise createFromParcel(Parcel in){

            return new ResistanceExercise(in);
        }
        public ResistanceExercise[] newArray(int size){
            return new ResistanceExercise[size];
        }
    };
    private ResistanceExercise(Parcel in){
        super(in);
        weight = in.readDouble();
        reps = in.readInt();
        sets = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest,flags);
        dest.writeDouble(this.weight);
        dest.writeInt(this.reps);
        dest.writeInt(this.sets);
    }
    @Override
    public int describeContents(){
        return 0;
    }


    public ResistanceExercise( double duration, String name, String comment, double weight, int reps, int sets){
        this(0,  duration, name, comment, weight, reps,sets);
    }

    public ResistanceExercise(int id, double duration, String name, String comment, double weight, int reps,int sets){
        super(id, duration, name, comment);
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
    }

    public String getName(){
        return super.getExerciseName();
    }
    public double getWeight(){
        return this.weight;
    }
    public void setWeight(Double weight){
        this.weight = weight;
    }
    public int getReps(){
        return this.reps;
    }
    public void setReps(int reps){
        this.reps = reps;
    }

    public int getSets(){
        return this.sets;
    }
    public void setSets(int sets){
        this.sets=sets;
    }
    public int getID(){
        return super.getId();
    }
   //returns in format-   Name, reps x sets, weight, comment(optional)
    public String toString(){
        String temp = "Exercise: "+this.name+" Weight: "+this.weight+" Sets x Reps: "+this.sets+"x"+this.reps+" Date: "+super.getDayCompleted();

        return temp;
    }
    //TODO will need to add parent Exercise values to the array as well, just get this working for now
    public String[] getAsArray(){
        String[] temp = new String[]{this.name,""+this.weight,""+this.sets,""+this.reps};
        return temp;
    }


}
