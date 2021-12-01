package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fragment.ThuChiChinh;
import fragment.ThuChiNgay;
import fragment.ThuChiThang;
import fragment.ThuChiTong;
import fragment.ThuChiTuan;


public class ViewPagerAdapterChinh extends FragmentStatePagerAdapter {
    public ViewPagerAdapterChinh(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ThuChiChinh();
            case 1:
                return new ThuChiNgay();
            case 2:
                return new ThuChiTuan();
            case 3:
                return new ThuChiThang();
            case 4:
                return new ThuChiTong();
            default:
                return new ThuChiChinh();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Chung";
                break;
            case 1:
                title="Ngày";
                break;
            case 2:
                title="Tuần";
                break;
            case 3:
                title="Tháng";
                break;
            case 4:
                title="Tổng";
                break;
        }
        return title;
    }
    
}
