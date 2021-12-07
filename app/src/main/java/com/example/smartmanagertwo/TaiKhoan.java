package com.example.smartmanagertwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taikhoan.ViewPagerAdapterTK;
import com.example.thongke.ThongKe;
import com.google.android.material.tabs.TabLayout;

public class TaiKhoan extends AppCompatActivity {
    TabLayout tab_taikhoan;
    ViewPager vp_taikhoan;
    TextView txtloaiTKnew;
//    ListView lvTaiKhoan;
//    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);

//        String[] items = {"Tiền mặt", "Tài khoản Ngân hàng","Thẻ tín dụng"};
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
//        lvTaiKhoan.setAdapter(adapter);

        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Tài khoản");



        linkViews();
        initData();

    }

    private void initData() {
        ViewPagerAdapterTK viewPagerAdapter = new ViewPagerAdapterTK(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_taikhoan.setAdapter(viewPagerAdapter);
        tab_taikhoan.setupWithViewPager(vp_taikhoan);
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
                Intent intents = new Intent(TaiKhoan.this, TaiKhoanSua.class);
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

    private void linkViews() {

        tab_taikhoan = findViewById(R.id.tab_taikhoan);
        vp_taikhoan = findViewById(R.id.vp_taikhoan);

        //lvTaiKhoan = findViewById(R.id.lvTaiKhoan);
    }
}