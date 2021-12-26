package com.example.muctieutietkiem.muctieu.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.muctieutietkiem.muctieu.fragment.muctieu_hoatdong_fragment;
import com.example.muctieutietkiem.muctieu.fragment.muctieu_hoanthanh_fragment;
import com.example.muctieutietkiem.muctieu.fragment.muctieu_tamdung_fragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new muctieu_hoatdong_fragment();
            case 1:
                return new muctieu_tamdung_fragment();
            case 2:
                return new muctieu_hoanthanh_fragment();
            default:
                return new muctieu_hoatdong_fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Hoạt động";
                break;
            case 1:
                title="Tạm dừng";
                break;
            case 2:
                title="Hoàn thành";
                break;
        }
        return title;
    }
}
