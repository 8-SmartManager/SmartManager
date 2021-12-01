package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fragment.ThuChiChinh;
import fragment.ThuChiNgay;
import fragment.ThuChiThang;
import fragment.ThuChiThemMoiChi;
import fragment.ThuChiThemMoiThu;
import fragment.ThuChiTong;
import fragment.ThuChiTuan;

public class ViewPagerAdapterThemMoi extends FragmentStatePagerAdapter {
    public ViewPagerAdapterThemMoi (@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ThuChiThemMoiChi();
            case 1:
                return new ThuChiThemMoiThu();

            default:
                return new ThuChiThemMoiChi();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Chi";
                break;
            case 1:
                title="Thu";
                break;
        }
        return title;
    }

}
