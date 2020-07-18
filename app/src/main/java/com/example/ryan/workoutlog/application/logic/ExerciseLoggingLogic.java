package com.example.ryan.workoutlog.application.logic;

import com.example.ryan.workoutlog.application.domain.CardioExercise;
import com.example.ryan.workoutlog.application.domain.Exercise;
import com.example.ryan.workoutlog.application.domain.ResistanceExercise;
import com.example.ryan.workoutlog.application.persistance.LoggedExercisePersistanceStub;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class ExerciseLoggingLogic {
    LoggedExercisePersistanceStub stub;
    Hashtable availableExercises = new Hashtable();
    public ExerciseLoggingLogic(LoggedExercisePersistanceStub stub){
       addStub(stub);


    }
    /*List of existing exercises, will also allow for user to add new ones that they want to store for the future
    Only need a name and type(resistance vs cardio)
    Use upper case to store to avoid case differences allowing user to submit for example, Squat and squat
    TODO is the case matching necessary?
    * */
    public void addExerciseType(String name){

        availableExercises.put(name.toUpperCase(),name);
    }
    private void addStub(LoggedExercisePersistanceStub stub){
        this.stub = stub;
    }
    public List<Exercise> getExercises(){
        return this.stub.getExercises();
    }
    //return a list of available resistance exercises for user to choose from
    public List<Exercise> getResistanceExercises(){

        List<Exercise> allExercises = stub.getExercises();
        List<Exercise> temp = new ArrayList<>();

        for (int i = 0; i <allExercises.size() ; i++) {
            if(allExercises.get(i) instanceof ResistanceExercise){
                temp.add(allExercises.get(i));
            }
        }
        return temp;
    }
    public List<Exercise> getCardioExercises(){

        List<Exercise> allExercises = stub.getExercises();
        List<Exercise> temp = new ArrayList<>();

        for (int i = 0; i <allExercises.size() ; i++) {
            if(allExercises.get(i) instanceof CardioExercise){
                temp.add(allExercises.get(i));
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
    //TODO make sure it's not possible to create Exercise object
    //also confirm that Object value is the proper type, do that at the presentation level?
    public Exercise editExercise(Exercise old,String valueToUpdate,String value){
        //Exercise updated = old;
        if(old instanceof ResistanceExercise) {
            switch (valueToUpdate) {
                case "Weight":
                    ((ResistanceExercise) old).setWeight(Double.valueOf(value));
                    break;
                case "Cardio":
                    break;

            }
        }
        else if(old instanceof CardioExercise){
            switch (valueToUpdate){
                case "distance":
                   try {
                       ((CardioExercise) old).setDistance(Double.valueOf(value));
                       stub.updateExercise(old);
                   }
                   catch (Exception e){
                       //TODO need a way to signal user that value wasn't updated
                       return old;
                   }
                   break;
            }
        }
        stub.updateExercise(old);
        return old;
    }

    //TODO throw exception if null or just return a null object and deal with it at caller?
    public Exercise getIndex(int position){
        if(position >=0) {
            return stub.getPosition(position);
        }
        //return stub.getExercises().get(position);
        return null;
    }
    //get resistance exercises the user has done
    /*public List<Exercise> getLoggedResistanceExercises(){

    }*/
    public List<Exercise> getExercisesFromDate(Date date){
        List<Exercise> temp = new ArrayList<>();
        List<Exercise> allLogged = stub.getExercises();
        for (int i = 0; i <allLogged.size() ; i++) {
            if(allLogged.get(i).getDayCompleted().compareTo(date)==0){
                temp.add(allLogged.get(i));
            }
        }

        return temp;
    }
}
