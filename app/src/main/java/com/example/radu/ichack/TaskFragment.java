package com.example.radu.ichack;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_tasks);

        return view;
    }

    public void updateList() {
        final DBHandler db = new DBHandler(getActivity().getBaseContext());
        List<String> tasksNames = new ArrayList<>();

        for (Task task : db.getAllTasks()) {
            tasksNames.add(task.getName());
        }

        ListAdapter adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, tasksNames);
        listView.setAdapter(adapter);
    }
}