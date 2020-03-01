package com.example.ryan.workoutlog.UnitTests;


import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Domain.Exercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.Application.Logic.ExerciseLoggingLogic;
import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
public class ExerciseLoggingLogicUnitTest {
    LoggedExerciesPersistanceStub stub = new LoggedExerciesPersistanceStub();
   /* @Override
    protected void setUp(){
        System.out.println("Set up");
        stub = new LoggedExerciesPersistanceStub(); //TODO using this makes some tests not run?
    }*/

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void retrieveResistanceExerciseList(){
        System.out.println("Runing retrieveResistanceExerciseList test");
        ExerciseLoggingLogic test = new ExerciseLoggingLogic(stub);
        List<Exercise> result = test.getResistanceExercises();


        //assertTrue(result != null && !result.isEmpty());
        //assertTrue(result.contains(exerciseToAdd));


        CardioExercise testCardio = new CardioExercise(0, 50, "Running", "", 5);
        test.addExercise(testCardio);
        assertTrue(test.getExercises().contains(testCardio));
        for (int i = 0; i <result.size() ; i++) {
            assertTrue(result.get(i) instanceof ResistanceExercise);
        }
    }
    @Test
    public void retrieveCardioExerciseList(){
        System.out.println("Runing retrieveCardioExerciseList test");
        ExerciseLoggingLogic test = new ExerciseLoggingLogic(stub);
        List<Exercise> result = test.getCardioExercises();

        ResistanceExercise testResistance = new ResistanceExercise(13, 0, "Squats", "", 225, 12, 1);
        test.addExercise(testResistance);
        assertTrue(test.getExercises().contains(testResistance));
        for (int i = 0; i <result.size() ; i++) {
            assertTrue(result.get(i) instanceof CardioExercise);
        }
    }

    @Test
    public void testDeleteExercise(){
        ExerciseLoggingLogic test = new ExerciseLoggingLogic(stub);
        CardioExercise sample =new CardioExercise(0,  50, "Running", "", 5);
        test.addExercise(sample);
        assertTrue(test.getExercises().contains(sample));
        //now test delete
        test.deleteExercise(sample);
        assertTrue(!test.getExercises().contains(sample));

    }

    @Test
    public void testEditExercise(){
        ExerciseLoggingLogic test = new ExerciseLoggingLogic(stub);
        CardioExercise sample =new CardioExercise(0,  50, "Running", "", 5);
        test.addExercise(sample);

        assertTrue(test.getExercises().contains(sample));
        //now test edit
        CardioExercise sampleResult = (CardioExercise) test.editExercise(sample,"distance","25");
        assertTrue(sampleResult.getDistance() == 25);
        CardioExercise sampleResult2 = (CardioExercise) test.editExercise(sample,"distance","invalid input");
        assertTrue(sampleResult2.getDistance()==25);
    }

    @Test
    public void testAddExercise() {
        ExerciseLoggingLogic test = new ExerciseLoggingLogic(new LoggedExerciesPersistanceStub());
        ResistanceExercise testAdd = new ResistanceExercise(13, 0, "Squats", "", 225, 12, 1);
        test.addExercise(testAdd);
        assertTrue(test.getResistanceExercises().contains(testAdd));
        assertTrue(test.getExercises().contains(testAdd));


    }

}
