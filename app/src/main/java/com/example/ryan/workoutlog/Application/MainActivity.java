package com.example.ryan.workoutlog.Application;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.ryan.workoutlog.Application.Persistance.LoggedExerciesPersistanceStub;
import com.example.ryan.workoutlog.Application.Presentation.ExerciseLoggingActivity;
import com.example.ryan.workoutlog.Application.Presentation.Profile;
import com.example.ryan.workoutlog.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity implements Serializable {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    LoggedExerciesPersistanceStub stub = new LoggedExerciesPersistanceStub();
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    public static void main(String[] args)
    {

    }
    public static final String EXTRA_MESSAGE = "com.example.ryan.workoutlog.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        //navigation drawer start
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        dl.addDrawerListener(t);
      t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.loggedExerciseNav:
                        Intent intent1 = new Intent(MainActivity.this, ExerciseLoggingActivity.class);
                        startActivity(intent1);
                       // openExerciseLogging();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
                    case R.id.profileMenu:
                            Intent profile = new Intent(MainActivity.this, Profile.class);
                            startActivity(profile);
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });

        //navigation drawer end
       // mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav =(BottomNavigationView) findViewById(R.id.main_nav);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.loggedExerciseNav:
                        Intent intent = new Intent(MainActivity.this, ExerciseLoggingActivity.class);

                        startActivity(intent);
                        break;
                    case R.id.profileMenu:
                        Intent profile = new Intent(MainActivity.this, Profile.class);
                        startActivity(profile);
                }
                return false;
            }
        });
    }


    public void openExerciseLogging(){
        Intent intent = new Intent(this, ExerciseLoggingActivity.class);
        startActivity(intent);
    }
    private void setupViews(){

       // EditText test = findViewById(R.id.editText);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
