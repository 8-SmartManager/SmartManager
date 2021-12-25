package com.example.thuchi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thuchi.adapter.XemActivityThangAdapter;
import com.example.thuchi.model.ThuChiXemActivity;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class ThuChiThang extends Fragment {

    ListView lvActivity;
    ArrayList<ThuChiXemActivity> activities;
    XemActivityThangAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_thang, container, false);

        lvActivity = view.findViewById(R.id.lvActivity);

        initData();
        loadData();
        addEvents();

        return view;
    }

    private void addEvents() {
        lvActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });
    }

    private void initData() {
        activities = new ArrayList<ThuChiXemActivity>();
        activities.add(new ThuChiXemActivity("Tháng 1", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 2", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 3", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 4", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 5", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 6", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 7", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 8", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 9", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 10", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 11", 0,0));
        activities.add(new ThuChiXemActivity("Tháng 12", 0,0));

    }

    private void loadData() {
        adapter = new XemActivityThangAdapter(getContext(),R.layout.item_thuchi_tuan_thang, activities);
        lvActivity.setAdapter(adapter);
    }
}
