package com.example.smartmanagertwo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muctieutietkiem.muctieu.TaoMucTieu;
import com.example.muctieutietkiem.muctieu.adapter.ViewPagerAdapter;
import com.example.smartmanagertwo.databinding.FragmentGalleryBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MucTieuTietKiemActivity extends Fragment {
    TabLayout tab_muctieu;
    ViewPager vp_muctieu;
    FloatingActionButton btnTaoMT;



    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_kehoach_muctieu_tietkiem,container,false);
        btnTaoMT=root.findViewById(R.id.btnTaoMT);
        tab_muctieu=root.findViewById(R.id.tab_muctieu);
        vp_muctieu=root.findViewById(R.id.vp_muctieu);
        initData();
        addEvents();
        return root;
    }

    private void initData() {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_muctieu.setAdapter(viewPagerAdapter);
        tab_muctieu.setupWithViewPager(vp_muctieu);
    }

    private void addEvents() {
        btnTaoMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), TaoMucTieu.class);
                startActivity(intent);
            }
        });
    }


}