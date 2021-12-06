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

import com.example.thongke.ThongKe;
import com.example.thongke.ThongKeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThongKeThuFragment extends Fragment {

    ListView lvThongKeThu;
    ArrayList<ThongKe> InfoTKThu;
    ThongKeAdapter adapter;
    public static MyDatabaseHelper db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_thu, container, false);

        lvThongKeThu = view.findViewById(R.id.lvThongKeThu);

        loadData();
        addEvents();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter = new ThongKeAdapter((Activity) getContext(), R.layout.thong_ke_item_layout,getDataFromDb());
        lvThongKeThu.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db = new MyDatabaseHelper(context);
        db.createSomeData();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThongKe> getDataFromDb(){
        InfoTKThu = new ArrayList<>();
        Cursor cursor = db.getData("SELECT (SUM("+MyDatabaseHelper.COL_THUCHI_AMOUNT+")/(SELECT SUM("+MyDatabaseHelper.COL_THUCHI_AMOUNT+") FROM "+MyDatabaseHelper.TBL_NAME_THUCHI+" WHERE "+MyDatabaseHelper.COL_THUCHI_TYPE+"='"+ "Thu' )),"+ MyDatabaseHelper.COL_THUCHI_NAME + " , " + MyDatabaseHelper.COL_THUCHI_TYPE+", SUM( " + MyDatabaseHelper.COL_THUCHI_AMOUNT + ") " + " FROM " + MyDatabaseHelper.TBL_NAME_THUCHI +" WHERE "+MyDatabaseHelper.COL_THUCHI_TYPE+"='"+ "Thu"+"' GROUP BY " + MyDatabaseHelper.COL_THUCHI_NAME);
        InfoTKThu.clear();
        while (cursor.moveToNext()){
            InfoTKThu.add(new ThongKe( cursor.getDouble(0), cursor.getString(1),cursor.getString(2),cursor.getDouble(3)));
        }
        cursor.close();
        return InfoTKThu;
    }

    private void addEvents() {
        lvThongKeThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ThongKeChiTietActivity.class);
                adapter = new ThongKeAdapter(getActivity(), R.layout.thong_ke_item_layout,InfoTKThu);
                ThongKe thongKe= (ThongKe) adapter.getItem(i);
                intent.putExtra("Thong Ke",thongKe);
                startActivity(intent);
            }
        });
    }

}
