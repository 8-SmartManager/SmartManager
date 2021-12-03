package com.example.smartmanagertwo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taikhoan.ViewPagerAdapterTK;
import com.example.thongke.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TaiKhoan extends AppCompatActivity {
    TabLayout tab_taikhoan;
    ViewPager vp_taikhoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
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
                Toast.makeText(this, "Bạn chọn Sửa loại tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnXoa:
                Toast.makeText(this, "Bạn chọn Xóa loại tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnHien_An:
                Toast.makeText(this, "Bạn chọn Hiện/Ẩn loại tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnThongKe:
                Toast.makeText(this, "Bạn chọn thống kê", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void linkViews() {

        tab_taikhoan = findViewById(R.id.tab_taikhoan);
        vp_taikhoan = findViewById(R.id.vp_taikhoan);
    }
}