package com.example.smartmanagertwo;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.adapter.ViewPagerAdapterChinh;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class                                                ThuChi extends AppCompatActivity {
    FloatingActionButton btnThemMoi;
    TabLayout tab_thuchi;
    ViewPager vp_thuchi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_chi);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Thu Chi");
        LinkViews();
        initData();
        addEvents();

    }

   

    private void addEvents() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuChi.this, ThuChiThemMoi.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        ViewPagerAdapterChinh viewPagerAdapter=new ViewPagerAdapterChinh(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_thuchi.setAdapter(viewPagerAdapter);
        tab_thuchi.setupWithViewPager(vp_thuchi);
    }

    private void LinkViews() {
        btnThemMoi=findViewById(R.id.btnThemMoiThuChi);
        tab_thuchi=findViewById(R.id.tab_thuchi);
        vp_thuchi=findViewById(R.id.vp_thuchi);
    }
}
