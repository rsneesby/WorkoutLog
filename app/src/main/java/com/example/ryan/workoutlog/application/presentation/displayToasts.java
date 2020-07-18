package com.example.ryan.workoutlog.application.presentation;

import android.content.Context;
import android.widget.Toast;

/*Will use this class to build Toasts based on various user inputs/application requirements
* Will accept a String and application context then display Toast
* */
public class displayToasts {
    public displayToasts(){

    }
    public void makeToast(String displayString, Context applicationContext){
        Toast temp = Toast.makeText(applicationContext,displayString,Toast.LENGTH_SHORT);
        temp.show();
    }
}
