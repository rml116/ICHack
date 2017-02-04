package com.example.radu.ichack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PageAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PageAdapter(FragmentManager fragMen, int numOfTabs) {
        super(fragMen);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            HabitFragment habits = new HabitFragment();
            return habits;
        }
        else {
            TaskFragment tasks = new TaskFragment();
            return tasks;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}