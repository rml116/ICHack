package com.example.radu.ichack;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MyDialogFragment extends DialogFragment {
  private DBHandler db;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    db = new DBHandler(getActivity().getBaseContext());

    View rootView = inflater.inflate(R.layout.custom_dialog, container, false);
    getDialog().setTitle("New Habit");

    final EditText et1 = (EditText)rootView.findViewById(R.id.editText1);

    Button save = (Button)rootView.findViewById(R.id.dismiss);

    save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String habit = et1.getText().toString();
        Habit habit1 = new Habit(1, habit, 0, 1, 3, false);
        db.addHabit(habit1);
        getDialog().dismiss();
      }
      });
    return rootView;
  }
}