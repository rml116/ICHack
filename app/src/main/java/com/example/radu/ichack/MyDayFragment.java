package com.example.radu.ichack;

import android.icu.util.Calendar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by radu on 04.02.2017.
 */

public class MyDayFragment extends Fragment {
  private DBHandler db;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view =  inflater.inflate(R.layout.myday_adapter, container, false);
    db = new DBHandler(getActivity().getBaseContext());
    TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

    tabLayout.addTab(tabLayout.newTab().setText("Habits"));
    tabLayout.addTab(tabLayout.newTab().setText("Tasks"));
    tabLayout.addTab(tabLayout.newTab().setText("Daily Progress"));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
    final PagerAdapter adapter = new PageAdapter(getFragmentManager(), tabLayout.getTabCount());
    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

    return view;
  }

  @Override
  public void onResume() {
    super.onResume();

    Calendar c = Calendar.getInstance();
    int day = c.get(Calendar.DAY_OF_MONTH);

    if (!(db.getDay(1).isPresent())) {
      db.addDay(day);
    } else if (!(db.getAllDays().get(0) == day)){
      db.updateDay(day);
      List<Habit> habits = db.getAllHabits();

      for (Habit habit : habits) {
        habit.setDone(false);
        db.updateHabit(habit);
      }
    }
  }
}
