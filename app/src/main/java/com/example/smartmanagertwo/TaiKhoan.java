package com.example.smartmanagertwo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taikhoan.TaiKhoanActivity;
import com.example.taikhoan.TaiKhoanAdapter;
import com.example.thongke.ThongKe;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoan extends AppCompatActivity {

    ListView lvTaiKhoanThu;
    ArrayList<TaiKhoanActivity> InfoTaiKhoanThu;
    TaiKhoanAdapter adapter;
    public static MyDatabaseHelper db;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_chinh);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Tài khoản");
        prepareDB();
        linkViews();
        loadData();
        addEvents();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taikhoan_tuychon_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnSua:
                Intent intents = new Intent(TaiKhoan.this, TaiKhoanChinhSua.class);
                startActivity(intents);
                Toast.makeText(this, "Bạn vừa chọn Sửa tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnXoa:
                Intent myintent = new Intent(TaiKhoan.this, TaiKhoanXoa.class);
                startActivity(myintent);
                Toast.makeText(this, "Bạn vừa chọn Xóa tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnThem:
                Intent intent = new Intent(TaiKhoan.this, TaiKhoanThem.class);
                startActivity(intent);
                Toast.makeText(this, "Bạn vừa chọn Thêm tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnThongKe:
                Intent intent_act = new Intent(TaiKhoan.this, ThongKe.class);
                startActivity(intent_act);
                Toast.makeText(this, "Bạn chọn thống kê", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

    private void linkViews() {
        lvTaiKhoanThu = findViewById(R.id.lvTaiKhoanThu);
    }
    private void prepareDB() {
        db = new MyDatabaseHelper(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter = new TaiKhoanAdapter(TaiKhoan.this,R.layout.item_tai_khoan_layout,getDataFromDb());
        lvTaiKhoanThu.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<TaiKhoanActivity> getDataFromDb(){
        InfoTaiKhoanThu = new ArrayList<>();
        Cursor cursor = db.getData("SELECT "+MyDatabaseHelper.COL_THUCHI_ACCOUNT + " , " + " SUM(" +MyDatabaseHelper.COL_THUCHI_AMOUNT + " ) " + " FROM " + MyDatabaseHelper.TBL_NAME_THUCHI + " GROUP BY " + MyDatabaseHelper.COL_THUCHI_ACCOUNT);
        InfoTaiKhoanThu.clear();
        while (cursor.moveToNext()){
            InfoTaiKhoanThu.add(new TaiKhoanActivity(cursor.getString(0),cursor.getDouble(1)));
        }
        cursor.close();
        return InfoTaiKhoanThu;
    }


    private void addEvents() {
        lvTaiKhoanThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TaiKhoan.this, TaiKhoanChiTietActivity.class);
                adapter = new TaiKhoanAdapter(TaiKhoan.this, R.layout.item_tai_khoan_layout,InfoTaiKhoanThu);
                TaiKhoanActivity taiKhoanActivity= (TaiKhoanActivity) adapter.getItem(i);
                intent.putExtra("Tai Khoan",taiKhoanActivity);
                startActivity(intent);
            }
        });
    }


}
