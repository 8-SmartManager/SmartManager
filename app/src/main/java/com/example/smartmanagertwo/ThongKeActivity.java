package com.example.smartmanagertwo;

import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.thongke.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThongKeActivity extends AppCompatActivity {
    TabLayout tab_thongKe;
    ViewPager vp_thongKe;

    Spinner spTime;
    ArrayList<String> timeList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Thống kê");
        linkViews();
        initData();
    }

    private void linkViews() {
        tab_thongKe = findViewById(R.id.tab_thongKe);
        vp_thongKe = findViewById(R.id.vp_thongKe);

        spTime = findViewById(R.id.mnSpinner);
    }
    private void initData() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_thongKe.setAdapter(viewPagerAdapter);
        tab_thongKe.setupWithViewPager(vp_thongKe);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.thong_ke_menu, menu);
        MenuItem item = menu.findItem(R.id.mnSpinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        timeList = new ArrayList<>();
        timeList.add("Hàng tuần");
        timeList.add("Hàng tháng");
        timeList.add("Hàng năm");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeList);

        spinner.setAdapter(adapter);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnLich:
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongKeActivity.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
