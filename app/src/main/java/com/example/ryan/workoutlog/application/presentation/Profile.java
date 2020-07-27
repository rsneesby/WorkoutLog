package com.example.ryan.workoutlog.application.presentation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ryan.workoutlog.R;
import com.example.ryan.workoutlog.application.MainActivity;
import com.example.ryan.workoutlog.application.logic.PageAdapter;
import com.example.ryan.workoutlog.application.persistance.LoggedExercisePersistanceStub;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class Profile extends AppCompatActivity {
/*
* Goals for this page:
* swipe Tabs-> info, goals/prs, recent activity?
* info: simple textviews probably
* goals/prs, graph/fillable bar to indicate progress towards goal, could maybe implement a 1rm calculator if user wants to use rep maxes to track
* recent activity, show workouts for previous day/week (option to toggle) or graph with recent weeks/longer?
* */
    LoggedExercisePersistanceStub exercisePersistanceStub = new LoggedExercisePersistanceStub();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        Toolbar toolbar = findViewById(R.id.toolbar);
       // toolbar.setTitle(getResources().getString(R.string.app_name));
        final TabLayout tabLayout = findViewById(R.id.tablayout);
        TabItem tabChats = findViewById(R.id.tab1);
        TabItem tabStatus = findViewById(R.id.tab2);
        TabItem tabCalls = findViewById(R.id.tab3);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        final BottomNavigationView mMainNav =(BottomNavigationView) findViewById(R.id.main_nav);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                        @Override
            public void onTabUnselected(TabLayout.Tab tab){

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab) {
                                                   viewPager.setCurrentItem(tab.getPosition());
                                                   if (tab.getPosition() == 1) {
                                                       tabLayout.setBackgroundColor(ContextCompat.getColor(Profile.this,
                                                               R.color.colorAccent));
                                                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                           getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,
                                                                   R.color.colorAccent));
                                                       }
                                                   } else if (tab.getPosition() == 2) {
                                                       tabLayout.setBackgroundColor(ContextCompat.getColor(Profile.this,
                                                               android.R.color.darker_gray));
                                                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                           getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,
                                                                   android.R.color.darker_gray));
                                                       }
                                                   } else {
                                                       tabLayout.setBackgroundColor(ContextCompat.getColor(Profile.this,
                                                               R.color.colorPrimary));
                                                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                           getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,
                                                                   R.color.colorPrimaryDark));
                                                       }
                                                   }

                                               }
                                           });
           /* @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }*/
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
