package com.example.radu.ichack;

import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HabitFragment extends Fragment {
    private ListView listView;
    private DBHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.habit_list, container, false);
        db = new DBHandler(getActivity().getBaseContext());
        listView = (ListView)view.findViewById(R.id.list_view_habits);
        updateList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void updateList() {
        final List<String> habitNames = new ArrayList<>();
        final List<Integer> habitIds = new ArrayList<>();
        final List<Boolean> habitDones = new ArrayList<>();

        for (Habit habit : db.getAllHabits()) {
            habitNames.add(habit.getName());
            habitIds.add(habit.getId());
            habitDones.add(habit.isDone());
        }

        final ListAdapter adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_checked, habitNames);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Habit habit = db.getHabit(habitIds.get(position)).get();

                if (!habit.isDone()) {
                    habit.setDone(true);
                    db.updateHabit(habit);
                    adapter.getItem(position);
                    CheckedTextView textView = (CheckedTextView)view;
                    textView.setChecked(true);
                    Toast.makeText(getActivity().getBaseContext(), "Habit completed!", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
    }
}