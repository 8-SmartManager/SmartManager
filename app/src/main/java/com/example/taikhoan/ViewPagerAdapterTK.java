package com.example.taikhoan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.smartmanagertwo.TaiKhoanThuFragment;

public class ViewPagerAdapterTK extends FragmentStatePagerAdapter {
    public ViewPagerAdapterTK (@NonNull FragmentManager fm, int behavior){
        super(fm,behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TaiKhoanThuFragment();
            case 1:
                return new TaiKhoanThuFragment();
            default:
                return new TaiKhoanThuFragment();
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
