package com.example.smartmanagertwo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nhacnho.NhacNho;
import com.example.nhacnho.NhacNhoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhacNhoActivity extends AppCompatActivity {
    ListView lvNhacNho;
    ArrayList<NhacNho> nhacNhos;
    NhacNhoAdapter adapter;
    FloatingActionButton btnThemMoi;
    ActivityResultLauncher activityResultLauncher;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacnho_main);
        linkViews();
        initData();
        loadData();
        addEvents();
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
            if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null)
            {
                nhacNhos.add((NhacNho) result.getData().getSerializableExtra("new"));

            }
        });
    }

    private void linkViews() {
        lvNhacNho=findViewById(R.id.lvNhacNho);
        btnThemMoi=findViewById(R.id.btnNhacNhoTao);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initData() {
        nhacNhos= new ArrayList<NhacNho>();
        nhacNhos.add(new NhacNho("Tiết kiệm","Mua xe","Hàng tháng", LocalDate.of(2021,11,20), Time.valueOf("08:00:00")));
        nhacNhos.add(new NhacNho("Chi","Tiền điện","Hàng tháng", LocalDate.of(2021,10,03), Time.valueOf("07:00:00")));
        nhacNhos.add(new NhacNho("Chi","Tiền điện","Một lần", LocalDate.of(2021,05,27), Time.valueOf("07:00:00")));

    }



    private void loadData() {
        adapter= new NhacNhoAdapter(NhacNhoActivity.this,R.layout.nhac_nho_item_layout,nhacNhos);
        lvNhacNho.setAdapter(adapter);
    }

    private void addEvents() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NhacNhoActivity.this,NhacNhoThemActivity.class);
                startActivity(intent);
            }
        });
        lvNhacNho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NhacNhoActivity.this, NhacNhoChiTietActivity.class);
                adapter= new NhacNhoAdapter(NhacNhoActivity.this,R.layout.nhac_nho_item_layout,nhacNhos);
                NhacNho nhacNho= (NhacNho) adapter.getItem(i);
                intent.putExtra("Nhac Nho",nhacNho);
                startActivity(intent);
            }
        });
    }
}