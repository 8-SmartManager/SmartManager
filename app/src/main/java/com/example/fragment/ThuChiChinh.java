package com.example.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.ActivityAdapter;
import com.example.model.ThuChiActivity;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.TaiKhoanSuaChiTiet;

import java.util.ArrayList;
import java.util.List;

import utils.Constant;

public class ThuChiChinh extends Fragment{

    public static MyDatabaseHelper db;

    TextView txtActivityName, txtActivityAccount, txtActivityAmount;
    ListView lvActivity;
    ActivityAdapter adapter;
    ArrayList<ThuChiActivity> activity;

    ThuChiActivity selectedActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_chinh, container, false);

        lvActivity = view.findViewById(R.id.lvActivity);

        prepareDb();
        linkViews();
        loadData();
        addEvents();

        return view;
    }

    private void prepareDb() {
        db = new MyDatabaseHelper(getContext());
        db.createSomeData();
    }

    private void linkViews() {

    }

    private void loadData() {
        adapter = new ActivityAdapter(getActivity(), R.layout.item_thuchi, getDataFromDb());
        lvActivity.setAdapter(adapter);
    }

    private List<ThuChiActivity> getDataFromDb() {
        activity = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THUCHI);
        activity.clear();
        while(cursor.moveToNext()){
//            activity.add(new ThuChiActivity(cursor.getInt(0), cursor.getString(1)));
            activity.add(new ThuChiActivity(cursor.getString(0), cursor.getString(1), cursor.getDouble(2)));
        }
        cursor.close();
        return activity;
    }

    private void addEvents() {
        lvActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedActivity = (ThuChiActivity) adapter.getItem(i);
                Intent intent = new Intent(getActivity(), TaiKhoanSuaChiTiet.class);
                intent.putExtra(Constant.SELECTED_ITEM, selectedActivity);
                startActivity(intent);



            }
        });
    }


}


