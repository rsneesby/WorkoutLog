package com.example.ryan.workoutlog.UnitTests;

import android.app.Instrumentation;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

//extending Instrumentation to use getContext to test DB
public class backgroundWorkerTests extends Instrumentation {
    Instrumentation instrumentation = new Instrumentation();



    BackgroundWorker backgroundWorker = new BackgroundWorker(super.getContext());
    @Test
    public void testInsert(){

    }
    @Test
    public void testRetrieveSingle(){

    }
    @Test
    public void testRetrieveAll(){

    }
    @Test
    public void testRetrieveDate(){

    }
    @Test
    public void testDelete(){

    }
    @Test
    public void testRetrieveType(){

    }

}
