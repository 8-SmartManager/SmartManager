package com.example.smartmanagertwo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.fragment.ThuChiChinh;
import com.example.thongke.ThongKe;
import com.example.thongke.ThongKeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThongKeChiFragment extends Fragment {

    ListView lvThongKe;
    ArrayList<ThongKe> InfoTK;
    ThongKeAdapter thongKeAdapter;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_chi, container, false);
        lvThongKe = view.findViewById(R.id.lvThongKe);

        linkViews();
        loadDataAdapter();
        addEvents();

        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadDataAdapter();
        super.onResume();
    }

    private void linkViews() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadDataAdapter() {
        thongKeAdapter = new ThongKeAdapter((Activity) getContext(), R.layout.item_thong_ke_layout,getDataFromDb());
        lvThongKe.setAdapter(thongKeAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThongKe> getDataFromDb(){
        InfoTK = new ArrayList<>();
        Cursor cursor = ThuChiChinh.db.getData("SELECT (SUM("+MyDatabaseHelper.COL_THUCHI_AMOUNT+")/(SELECT SUM("+MyDatabaseHelper.COL_THUCHI_AMOUNT+") FROM "+MyDatabaseHelper.TBL_NAME_THUCHI+" WHERE "+MyDatabaseHelper.COL_THUCHI_TYPE+"='"+ "Chi' )),"+ MyDatabaseHelper.COL_THUCHI_NAME + " , " + MyDatabaseHelper.COL_THUCHI_TYPE+", SUM( " + MyDatabaseHelper.COL_THUCHI_AMOUNT + ") " + " FROM " + MyDatabaseHelper.TBL_NAME_THUCHI +" WHERE "+MyDatabaseHelper.COL_THUCHI_TYPE+"='"+ "Chi"+"' GROUP BY " + MyDatabaseHelper.COL_THUCHI_NAME);
        InfoTK.clear();
        while (cursor.moveToNext()){
            InfoTK.add(new ThongKe( cursor.getDouble(0), cursor.getString(1),cursor.getString(2),cursor.getDouble(3)));
        }
        cursor.close();
        return InfoTK;
    }

    private void addEvents() {
        lvThongKe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ThongKeChiTietActivity.class);
                thongKeAdapter = new ThongKeAdapter(getActivity(), R.layout.item_thong_ke_layout,InfoTK);
                ThongKe thongKe= (ThongKe) thongKeAdapter.getItem(i);
                intent.putExtra("Thong Ke",thongKe);
                startActivity(intent);
            }
        });
    }
}