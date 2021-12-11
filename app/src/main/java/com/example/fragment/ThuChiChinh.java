package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.adapter.ActivityAdapter;
import com.example.model.ThuChiActivity;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.ThongKeChinhSua;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utils.Constant;

public class ThuChiChinh extends Fragment{

    public static MyDatabaseHelper db;

    TextView txtActivityName, txtActivityAccount, txtActivityAmount,txtTongThu,txtTongChi;
    ListView lvActivity;
    ActivityAdapter adapter;
    ArrayList<ThuChiActivity> activity;

    ThuChiActivity selectedActivity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_chinh, container, false);

        lvActivity = view.findViewById(R.id.lvActivity);
        txtTongThu=view.findViewById(R.id.txtTongThu);
        txtTongChi=view.findViewById(R.id.txtTongChi);

        prepareDb();


        addEvents();

        return view;
    }

    private void prepareDb() {
        db = new MyDatabaseHelper(getContext());
        db.createSomeData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter = new ActivityAdapter(getActivity(), R.layout.item_thuchi, getDataFromDb());
        lvActivity.setAdapter(adapter);
        double thu=0,chi=0;
        for (ThuChiActivity a:activity
             ) {
            if(a.getActivityType().equals("Thu")){
                thu+=a.getActivityAmount();
            }else {
                chi+=a.getActivityAmount();
            }
        }
        txtTongThu.setText(String.format("%,.0f",thu));
        txtTongChi.setText(String.format("%,.0f",chi));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThuChiActivity> getDataFromDb() {
        activity = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THUCHI);
        activity.clear();
        while(cursor.moveToNext()){
//            activity.add(new ThuChiActivity(cursor.getInt(0), cursor.getString(1)));
            activity.add(new ThuChiActivity(cursor.getInt(0), LocalDate.parse(cursor.getString(5)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
        }
        cursor.close();
        return activity;
    }

    private void addEvents() {
        lvActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedActivity = (ThuChiActivity) adapter.getItem(i);

                Intent intent = new Intent(getContext(), ThongKeChinhSua.class);
                intent.putExtra("ThongKeChiTiet", selectedActivity);
                startActivity(intent);



            }
        });
    }




}


