package com.example.ryan.workoutlog.Application.Presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ryan.workoutlog.Application.Domain.CardioExercise;
import com.example.ryan.workoutlog.Application.Domain.ResistanceExercise;
import com.example.ryan.workoutlog.Application.MainActivity;
import com.example.ryan.workoutlog.R;

public class Profile extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mMainNav =(BottomNavigationView) findViewById(R.id.main_nav);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.loggedExerciseNav:
                        Intent intent = new Intent(Profile.this, ExerciseLoggingActivity.class);

                        startActivity(intent);
                        break;
                    case R.id.home_nav:
                        Intent intent1 = new Intent(Profile.this, MainActivity.class);
                        startActivity(intent1);
                }
                return true;
            }
        });

        setUpViews();

    }
    private void setUpViews(){






    }
}