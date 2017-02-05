package com.example.radu.ichack;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DailyProgress extends Fragment {
  private WaveLoadingView mWaveLoadingView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.daily_progress, container, false);

    DBHandler dbHandler = new DBHandler(getContext());
    int progress;

    if (dbHandler.getHabitsCount() == 0) {
      progress = 0;
    } else {
      progress = dbHandler.getHabitsDoneCount() * 100 / dbHandler.getHabitsCount();
    }

    mWaveLoadingView = (WaveLoadingView) view.findViewById(R.id.waveLoadingView);

    mWaveLoadingView.setCenterTitle("Daily Progress: " + Integer.toString(progress) + "%");
    mWaveLoadingView.startAnimation();

    mWaveLoadingView.setProgressValue(progress);

    mWaveLoadingView.setBorderWidth(40);

    mWaveLoadingView.setAmplitudeRatio(50);

    mWaveLoadingView.setWaveColor(Color.parseColor("#CDDC39"));

    mWaveLoadingView.setWaveBgColor(Color.TRANSPARENT);

    mWaveLoadingView.setBorderColor(Color.parseColor("#00796B"));
    mWaveLoadingView.setAnimDuration(3000);

    return view;
  }

}