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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.list_view_tasks);
        DBHandler db = new DBHandler(getActivity().getBaseContext());
        List<String> taskNames = new ArrayList<>();

        for (Task task : db.getAllTasks()) {
            taskNames.add(task.getName());
        }

        ListAdapter adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, taskNames);
        listView.setAdapter(adapter);

        return view;
    }
}