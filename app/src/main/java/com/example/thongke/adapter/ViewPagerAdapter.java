package com.example.thongke.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.thongke.fragment.ThongKeChiFragment;
import com.example.thongke.fragment.ThongKeThuFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter (@NonNull FragmentManager fm, int behavior){
        super(fm,behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ThongKeChiFragment();
            case 1:
                return new ThongKeThuFragment();
            default:
                return new ThongKeChiFragment();
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
