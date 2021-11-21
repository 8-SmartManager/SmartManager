package com.example.smartmanagertwo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhacnho.NhacNho;
import com.example.thongke.ThongKe;
import com.example.thongke.ThongKeAdapter;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {

    Spinner spTime;
    ArrayList<String> timeList;
    ArrayAdapter<String> adapter;


    ListView lvThongKe;
    ArrayList<ThongKe> InfoTK;
    ThongKeAdapter thongKeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        linkViews();
        loadData();
        initData();
        loadDataAdapter();
        addEvents();
    }

    private void linkViews() {
        spTime = findViewById(R.id.spTime);
        lvThongKe = findViewById(R.id.lvThongKe);
    }

    private void loadData() {
        timeList = new ArrayList<>();
        timeList.add("Hàng tuần");
        timeList.add("Hàng tháng");
        timeList.add("Hàng năm");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeList);
        spTime.setAdapter(adapter);
    }

    private void initData() {
        InfoTK = new ArrayList<ThongKe>();
        InfoTK.add(new ThongKe("50%", "Ăn uống", 2000000.0));
        InfoTK.add(new ThongKe("27%", "Giải trí", 500000.0));
        InfoTK.add(new ThongKe("23%", "Giáo dục", 300000.0));
    }

    private void loadDataAdapter() {
        thongKeAdapter = new ThongKeAdapter(ThongKeActivity.this, R.layout.thong_ke_item_layout,InfoTK);
        lvThongKe.setAdapter(thongKeAdapter);
    }

    private void addEvents() {
        lvThongKe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ThongKeActivity.this, ThongKeChiTiet.class);
                thongKeAdapter = new ThongKeAdapter(ThongKeActivity.this, R.layout.thong_ke_item_layout,InfoTK);
                ThongKe thongKe= (ThongKe) thongKeAdapter.getItem(i);
                intent.putExtra("Thong Ke",thongKe);
                //startActivity(intent);
            }
        });
    }



}