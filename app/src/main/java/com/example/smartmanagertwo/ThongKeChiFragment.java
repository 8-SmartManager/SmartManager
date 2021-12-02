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

public class ThongKeChiFragment extends Fragment {


//    Spinner spTime;
//    ArrayList<String> timeList;
//    ArrayAdapter<String> adapter;


    ListView lvThongKe;
    ArrayList<ThongKe> InfoTK;
    ThongKeAdapter thongKeAdapter;
    public static MyDatabaseHelper db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_chi, container, false);
        lvThongKe = view.findViewById(R.id.lvThongKe);


        //prepareDb();
        linkViews();
        loadData();
//        initData();
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
        //spTime = findViewById(R.id.spTime);
//        lvThongKe = view.findViewById(R.id.lvThongKe);
    }

//    private void prepareDb() {
//        db = new MyDatabaseHelper(this);
//        db.createSomeData();
//    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadDataAdapter() {
        thongKeAdapter = new ThongKeAdapter((Activity) getContext(), R.layout.thong_ke_item_layout,getDataFromDb());
        lvThongKe.setAdapter(thongKeAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db = new MyDatabaseHelper(context);
        db.createSomeThongKeChiTietData();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThongKe> getDataFromDb(){
        InfoTK = new ArrayList<>();
        Cursor cursor = db.getData("SELECT " + MyDatabaseHelper.COL_TKCHICHITIET_THELOAI + " , " + " SUM(" + MyDatabaseHelper.COL_TKCHICHITIET_MONEY + " ) " + " FROM " + MyDatabaseHelper.TBL_NAME_THONG_KE_CHI_CHI_TIET + " GROUP BY " + MyDatabaseHelper.COL_TKCHICHITIET_THELOAI);
//        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THONG_KE_CHI_CHI_TIET);
        InfoTK.clear();
        while (cursor.moveToNext()){
            InfoTK.add(new ThongKe(null, cursor.getString(0),cursor.getDouble(1)));
        }
        cursor.close();
        return InfoTK;
    }

//Spinner
    private void loadData() {
//        timeList = new ArrayList<>();
//        timeList.add("Hàng tuần");
//        timeList.add("Hàng tháng");
//        timeList.add("Hàng năm");
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeList);
//        spTime.setAdapter(adapter);
    }

//    private void initData() {
//        InfoTK = new ArrayList<ThongKe>();
//        InfoTK.add(new ThongKe("50%", "Ăn uống", 2000000.0));
//        InfoTK.add(new ThongKe("27%", "Giải trí", 500000.0));
//        InfoTK.add(new ThongKe("23%", "Giáo dục", 300000.0));
//    }



    private void addEvents() {
        lvThongKe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ThongKeChiChiTietActivity.class);
                thongKeAdapter = new ThongKeAdapter(getActivity(), R.layout.thong_ke_item_layout,InfoTK);
                ThongKe thongKe= (ThongKe) thongKeAdapter.getItem(i);
                intent.putExtra("Thong Ke",thongKe);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.thong_ke_menu, menu);
//
//        MenuItem item = menu.findItem(R.id.mnSpinner);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
//        timeList = new ArrayList<>();
//        timeList.add("Hàng tuần");
//        timeList.add("Hàng tháng");
//        timeList.add("Hàng năm");
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeList);
//
//        spinner.setAdapter(adapter);
//
//        return super.onCreateOptionsMenu(menu);
//    }
}