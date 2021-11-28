package com.example.smartmanagertwo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thongke.ThongKeChiTiet;
import com.example.thongke.ThongKeChiTietAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

public class ThongKeChiTietActivity extends AppCompatActivity {

    ImageButton btnBack;

    ListView lvThongKeChiTiet;
    ArrayList<ThongKeChiTiet> InfoTKChiTiet;
    ThongKeChiTietAdapter chiTietAdapter;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_chi_tiet);

        linkViews();

        initData();
        loadData();
        addEvents();

    }

    private void linkViews() {
        btnBack = findViewById(R.id.btnThongKeChiTietBack);
        lvThongKeChiTiet = findViewById(R.id.lvThongKeChiTiet);

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initData() {
        InfoTKChiTiet = new ArrayList<ThongKeChiTiet>();
        InfoTKChiTiet.add(new ThongKeChiTiet("Tiền mặt", "Giải trí", 200000, LocalDate.of(2021, 11, 27)));
        InfoTKChiTiet.add(new ThongKeChiTiet("Tài khoản ngân hàng", "Mua sắm", 500000, LocalDate.of(2021, 11, 27)));
        InfoTKChiTiet.add(new ThongKeChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
        InfoTKChiTiet.add(new ThongKeChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
        InfoTKChiTiet.add(new ThongKeChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
        InfoTKChiTiet.add(new ThongKeChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
    }

    private void loadData() {
        chiTietAdapter = new ThongKeChiTietAdapter(ThongKeChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, InfoTKChiTiet);
        lvThongKeChiTiet.setAdapter(chiTietAdapter);
    }

    private void addEvents() {
        // Nút back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // Tạo sự kiện listview
        lvThongKeChiTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ThongKeChiTietActivity.this, SuaHoatDong.class);
                chiTietAdapter = new ThongKeChiTietAdapter(ThongKeChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, InfoTKChiTiet);
                ThongKeChiTiet thongKeChiTiet = (ThongKeChiTiet) chiTietAdapter.getItem(i);
                intent.putExtra("ThongKeChiTiet", thongKeChiTiet);
                startActivity(intent);
            }
        });

    }
}
