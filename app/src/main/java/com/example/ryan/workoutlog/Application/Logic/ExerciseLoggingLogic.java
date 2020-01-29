package com.example.ryan.workoutlog.Application.Logic;

import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ExerciseLoggingLogic {
    LoggedExerciesPersistanceStub stub;
    public ExerciseLoggingLogic(){
        stub = new LoggedExerciesPersistanceStub();
    }
    public List<Exercise> getExercises(){
        return this.stub.getExercises();
    }
    //return a list of available resistance exercises for user to choose from
    public List<Exercise> getResistanceExercises(){
        ListIterator<Exercise> litr = null;
        List<Exercise> temp = new ArrayList<>();
        litr=temp.listIterator();
        while(litr.hasNext()){
            if(litr.next() instanceof ResistanceExercise){
                temp.add(litr.next());
            }
        }
        return temp;
    }
    public void addExercise(Exercise exercise){
        stub.insertExercise(exercise);
    }
    public void deleteExercise(Exercise exercise){
        stub.deleteExercise(exercise);
    }
    //TODO how to update? update values 1 at a time but replace whole object if >1 value updating?
    public Exercise editExercise(Exercise old,String valueToUpdate,Object value){
        Exercise updated = old;
        if(updated instanceof ResistanceExercise) {
            switch (valueToUpdate) {
                case "Weight":
                    ((ResistanceExercise) updated).setWeight((Double) value);
                    break;

            }
        }
        stub.updateExercise(updated);
        return updated;
    }
    //get resistance exercises the user has done
    /*public List<Exercise> getLoggedResistanceExercises(){

    }*/

}
