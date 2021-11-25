package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.muctieutietkiem.packages.TaoMucTieu;
import com.example.muctieutietkiem.packages.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class kehoach_muctieu_tietkiem extends AppCompatActivity {
    TabLayout tab_muctieu;
    ViewPager vp_muctieu;
    FloatingActionButton btnTaoMT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kehoach_muctieu_tietkiem);
        LinkViews();
        initData();
        addEvents();
    }

    private void initData() {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_muctieu.setAdapter(viewPagerAdapter);
        tab_muctieu.setupWithViewPager(vp_muctieu);
    }

    private void addEvents() {
        btnTaoMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(kehoach_muctieu_tietkiem.this, TaoMucTieu.class);
                startActivity(intent);
            }
        });
    }

    private void LinkViews() {
        btnTaoMT=findViewById(R.id.btnTaoMT);
        tab_muctieu=findViewById(R.id.tab_muctieu);
        vp_muctieu=findViewById(R.id.vp_muctieu);



    }
}