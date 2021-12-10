package com.example.smartmanagertwo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // hỏi vào thứ 5-> làm sao để chạy nhấn fragment k lên ô chữ
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        View v = getCurrentFocus();
//        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
//                v instanceof EditText &&
//                !v.getClass().getName().startsWith("android.webkit.")) {
//            int[] sourceCoordinates = new int[2];
//            v.getLocationOnScreen(sourceCoordinates);
//            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
//            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];
//
//            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
//                hideKeyboard(this);
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void hideKeyboard(Activity activity) {
//        if (activity != null && activity.getWindow() != null) {
//            activity.getWindow().getDecorView();
//            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm != null) {
//                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
//            }
//        }
//    }

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
