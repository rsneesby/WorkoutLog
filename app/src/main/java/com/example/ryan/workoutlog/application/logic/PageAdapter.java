package com.example.ryan.workoutlog.application.logic;

import com.example.ryan.workoutlog.application.presentation.recentActivity;
import com.example.ryan.workoutlog.application.presentation.userGoals;
import com.example.ryan.workoutlog.application.presentation.userInfo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    public PageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new userInfo();
            case 1:
                return new userGoals();
            case 2:
                return new recentActivity();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
