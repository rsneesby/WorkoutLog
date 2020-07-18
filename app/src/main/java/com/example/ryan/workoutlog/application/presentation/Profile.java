package com.example.ryan.workoutlog.application.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ryan.workoutlog.application.MainActivity;
import com.example.ryan.workoutlog.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
/*
* Goals for this page:
* swipe Tabs-> info, goals/prs, recent activity?
* info: simple textviews probably
* goals/prs, graph/fillable bar to indicate progress towards goal, could maybe implement a 1rm calculator if user wants to use rep maxes to track
* recent activity, show workouts for previous day/week (option to toggle) or graph with recent weeks/longer?
* */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView mMainNav =(BottomNavigationView) findViewById(R.id.main_nav);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.loggedExerciseNav:
                        Intent intent1 = new Intent(Profile.this, ExerciseLoggingActivity.class);

                        startActivity(intent1);
                        break;
                    case R.id.home_nav:
                        Intent intent = new Intent(Profile.this, MainActivity.class);
                        startActivity(intent);
                }
                return false;
            }
        });
        setUpViews();

    }


    @SuppressWarnings("EmptyMethod")
    protected void setUpViews(){

    }
}
