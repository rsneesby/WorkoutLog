package com.example.ryan.workoutlog.Application.Presentation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ryan.workoutlog.Application.MainActivity;
import com.example.ryan.workoutlog.R;

public class Profile extends Activity {


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


    protected void setUpViews(){

    }
}
