package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adapter.ViewPagerAdapterChinh;
import com.example.adapter.ViewPagerAdapterThemMoi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class ThuChiThemMoi extends AppCompatActivity {


    TabLayout tab_thuchiThemmoi;
    ViewPager vp_thuchiThemmoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_chi_them_moi);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Thêm Thu Chi");


        LinkViews();
        initData();
        addEvents();

    }

    private void addEvents() {

    }

    private void initData() {
        ViewPagerAdapterThemMoi viewPagerAdapter=new ViewPagerAdapterThemMoi(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_thuchiThemmoi.setAdapter(viewPagerAdapter);
        tab_thuchiThemmoi.setupWithViewPager(vp_thuchiThemmoi);
    }

    private void LinkViews() {

        tab_thuchiThemmoi=findViewById(R.id.tab_thuchiThemmoi);
        vp_thuchiThemmoi=findViewById(R.id.vp_thuchiThemmoi);
    }
}
