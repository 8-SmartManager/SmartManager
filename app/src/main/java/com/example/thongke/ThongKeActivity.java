package com.example.thongke;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.smartmanagertwo.R;
import com.example.thongke.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThongKeActivity extends Fragment {
    TabLayout tab_thongKe;
    ViewPager vp_thongKe;
    public static String time;

    ArrayList<String> timeList;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_thong_ke,container,false);
        tab_thongKe = root.findViewById(R.id.tab_thongKe);
        vp_thongKe = root.findViewById(R.id.vp_thongKe);
        initData();
        return root;

    }



    private void initData() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_thongKe.setAdapter(viewPagerAdapter);
        tab_thongKe.setupWithViewPager(vp_thongKe);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getActivity().getMenuInflater().inflate(R.menu.thong_ke_menu, menu);
//        MenuItem item = menu.findItem(R.id.mnSpinner);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
//        timeList = new ArrayList<>();
//        timeList.add("Hàng tuần");
//        timeList.add("Hàng tháng");
//        timeList.add("Hàng năm");
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, timeList);
//
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                time=adapter.getItem(i);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.thong_ke_menu, menu);
        MenuItem item = menu.findItem(R.id.mnSpinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        timeList = new ArrayList<>();
        timeList.add("Hàng tuần");
        timeList.add("Hàng tháng");
        timeList.add("Hàng năm");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, timeList);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time=adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        super.onCreateOptionsMenu(menu, inflater);
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),callBack,
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
