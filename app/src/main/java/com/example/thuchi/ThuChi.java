package com.example.thuchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.thuchi.adapter.ViewPagerAdapterChinh;
import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class  ThuChi extends Fragment {
    FloatingActionButton btnThemMoi;
    TabLayout tab_thuchi;
    ViewPager vp_thuchi;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_thu_chi,container,false);
        btnThemMoi=root.findViewById(R.id.btnThemMoiThuChi);
        tab_thuchi=root.findViewById(R.id.tab_thuchi);
        vp_thuchi=root.findViewById(R.id.vp_thuchi);
        initData();
        addEvents();
        return root;
    }
    private void addEvents() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThuChiThemMoi.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        ViewPagerAdapterChinh viewPagerAdapter=new ViewPagerAdapterChinh(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_thuchi.setAdapter(viewPagerAdapter);
        tab_thuchi.setupWithViewPager(vp_thuchi);
    }

    private void LinkViews() {

    }
}
