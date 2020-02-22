package com.example.ryan.workoutlog.Application.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ryan.workoutlog.Application.Domain.DomainInterfaces.ExerciseInterface;

import java.util.Date;

public class Exercise implements ExerciseInterface,Parcelable{
    public Date dayCompleted;
    public double duration;
    public String name;
    public String comment;

    protected int id;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Exercise createFromParcel(Parcel in){
            return new Exercise(in);
        }
        public Exercise[] newArray(int size){
            return new Exercise[size];
        }
    };
    //private final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public Exercise(int newId, double newDuration, String name, String comment){
        this.id = newId;
        this.dayCompleted = dayCompleted;
        this.duration = newDuration;
        this.name = name;
        this.comment = comment;
    }
    //parcelling constructor
    protected Exercise(Parcel in){
        this.id = in.readInt();
        //this.dayCompleted = new Date();
        this.duration = in.readDouble();
        this.name = in.readString();
        this.comment = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.id);
        //dest.writeLong(this.dayCompleted.getTime());
        dest.writeDouble(this.duration);
        dest.writeString(this.name);
        dest.writeString(this.comment);
    }
    @Override
    public int describeContents(){
        return 0;
    }
    public Exercise( double newDuration, String name, String comment){
        this(0,  newDuration, name, comment);
    }

    public int getId(){
        return this.id;
    }
    public double getDuration(){
        return this.duration;
    }
    public double setDuration(double duration){
        return this.duration = duration;
    }
    public String getExerciseName(){
        return this.name;
    }

    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }

    public Exercise(){
        this(0, 0, null, null);
    }


}
