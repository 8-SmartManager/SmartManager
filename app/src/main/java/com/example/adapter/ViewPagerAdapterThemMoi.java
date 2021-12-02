package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.fragment.ThuChiChinh;
import com.example.fragment.ThuChiNgay;
import com.example.fragment.ThuChiThang;
import com.example.fragment.ThuChiThemMoiChi;
import com.example.fragment.ThuChiThemMoiThu;
import com.example.fragment.ThuChiTong;
import com.example.fragment.ThuChiTuan;

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
