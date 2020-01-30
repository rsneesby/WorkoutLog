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
public class AccessLoggedExerciseUnitTest {
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

        ListIterator<Exercise> litr = null;
        test.addExercise(new CardioExercise(0, new Date(), 50, "Running", "", 5));
        litr=result.listIterator();
        while(litr.hasNext()){
            System.out.println(litr.next().getClass());
            assertTrue(litr.next() instanceof ResistanceExercise);
        }
    }

    @Test
    public void testDeleteExercise(){
        ExerciseLoggingLogic test = new ExerciseLoggingLogic(stub);
        CardioExercise sample =new CardioExercise(0, new Date(), 50, "Running", "", 5);
        test.addExercise(sample);
        assertTrue(test.getExercises().contains(sample));
        //now test delete
        test.deleteExercise(sample);
        assertTrue(!test.getExercises().contains(sample));

    }

    @Test
    public void testEditExercise(){
        ExerciseLoggingLogic test = new ExerciseLoggingLogic(stub);
        CardioExercise sample =new CardioExercise(0, new Date(), 50, "Running", "", 5);
        test.addExercise(sample);

        assertTrue(test.getExercises().contains(sample));
        //now test edit
        CardioExercise sampleResult = (CardioExercise) test.editExercise(sample,"distance","25");
        assertTrue(sampleResult.getDistance() == 25);
        CardioExercise sampleResult2 = (CardioExercise) test.editExercise(sample,"distance","invalid input");
        assertTrue(sampleResult2.getDistance()==25);
    }
}
