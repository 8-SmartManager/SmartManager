package com.example.thuchi.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thuchi.adapter.XemActivityNgayAdapter;
import com.example.thuchi.model.ThuChiXemActivity;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class ThuChiNgay extends Fragment {

    GridView gvActivity;
    ArrayList<ThuChiXemActivity> activities;
    XemActivityNgayAdapter adapter;

    ThuChiXemActivity selectedActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_ngay, container, false);

        gvActivity = view.findViewById(R.id.gvActivity);

        initData();
        loadData();
        addEvents();

        return view;
    }

    private void addEvents() {
        gvActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openDialog();

            }
        });
    }

    private void openDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_show_activity);

        dialog.show();
    }

    private void loadData() {
        adapter = new XemActivityNgayAdapter(this.getActivity(),R.layout.item_thuchi_ngay, activities);
        gvActivity.setAdapter(adapter);
    }

    private void initData() {
        activities = new ArrayList<ThuChiXemActivity>();
        activities.add(new ThuChiXemActivity("1", 0,0));
        activities.add(new ThuChiXemActivity("2", 0,0));
        activities.add(new ThuChiXemActivity("3", 0,0));
        activities.add(new ThuChiXemActivity("4", 0,0));
        activities.add(new ThuChiXemActivity("5", 0,0));
        activities.add(new ThuChiXemActivity("6", 0,0));
        activities.add(new ThuChiXemActivity("7", 0,0));
        activities.add(new ThuChiXemActivity("8", 0,0));
        activities.add(new ThuChiXemActivity("9", 0,0));
        activities.add(new ThuChiXemActivity("10", 0,0));
        activities.add(new ThuChiXemActivity("11", 0,0));
        activities.add(new ThuChiXemActivity("12", 0,0));
        activities.add(new ThuChiXemActivity("13", 0,0));
        activities.add(new ThuChiXemActivity("14", 0,0));
        activities.add(new ThuChiXemActivity("15", 0,0));
        activities.add(new ThuChiXemActivity("16", 0,0));
        activities.add(new ThuChiXemActivity("17", 0,0));
        activities.add(new ThuChiXemActivity("18", 2000000,2000000));
        activities.add(new ThuChiXemActivity("19", 0,0));
        activities.add(new ThuChiXemActivity("20", 0,0));
        activities.add(new ThuChiXemActivity("21", 0,0));
        activities.add(new ThuChiXemActivity("22", 0,0));
        activities.add(new ThuChiXemActivity("23", 0,0));
        activities.add(new ThuChiXemActivity("24", 0,0));
        activities.add(new ThuChiXemActivity("25", 0,0));
        activities.add(new ThuChiXemActivity("26", 0,0));
        activities.add(new ThuChiXemActivity("27", 0,0));
        activities.add(new ThuChiXemActivity("28", 0,0));
        activities.add(new ThuChiXemActivity("29", 0,0));
        activities.add(new ThuChiXemActivity("30", 0,0));
    }
}
