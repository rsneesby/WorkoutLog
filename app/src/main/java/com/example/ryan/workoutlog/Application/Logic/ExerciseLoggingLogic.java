package com.example.ryan.workoutlog.Application.Logic;

import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;

import java.util.ArrayList;
import java.util.List;

public class ExerciseLoggingLogic {
    LoggedExerciesPersistanceStub stub;
    public ExerciseLoggingLogic(){
        stub = new LoggedExerciesPersistanceStub();
    }
    public List<Exercise> getExercises(){
        return this.stub.getExercises();
    }

}
