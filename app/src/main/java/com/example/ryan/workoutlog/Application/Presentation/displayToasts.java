package com.example.ryan.workoutlog.Application.Presentation;

import android.content.Context;
import android.widget.Toast;

/*Will use this class to build Toasts based on various user inputs/application requirements
* Will accept a String and application context then display Toast
* */
public class displayToasts {
    public displayToasts(){

    }
    public Toast makeToast(String displayString, Context applicationContext){
        Context context = applicationContext;
        CharSequence text = "Deleted";
        int duration = Toast.LENGTH_SHORT;
        Toast temp = Toast.makeText(context,text,duration);
        return temp;
    }
}
