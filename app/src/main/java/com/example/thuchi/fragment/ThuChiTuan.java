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

import com.example.thuchi.adapter.XemActivityTuanAdapter;
import com.example.thuchi.model.ThuChiXemActivity;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class ThuChiTuan extends Fragment {

   ListView lvActivity;
    ArrayList<ThuChiXemActivity> activities;
    XemActivityTuanAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_tuan, container, false);

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
        activities.add(new ThuChiXemActivity("1/12 - 8/12", 0,0));
        activities.add(new ThuChiXemActivity("9/12 - 15/12", 0,0));
        activities.add(new ThuChiXemActivity("16/12 - 23/12", 0,0));
        activities.add(new ThuChiXemActivity("24/12 - 31/12", 0,0));
    }

    private void loadData() {
        adapter = new XemActivityTuanAdapter(getContext(),R.layout.item_thuchi_tuan_thang, activities);
        lvActivity.setAdapter(adapter);
    }
}
