package com.example.radu.ichack;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by radu on 04.02.2017.
 */

public class MyDayFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view =  inflater.inflate(R.layout.myday_adapter, container, false);
    TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

    tabLayout.addTab(tabLayout.newTab().setText("Habits"));
    tabLayout.addTab(tabLayout.newTab().setText("Tasks"));
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
}
