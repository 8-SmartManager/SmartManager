package com.example.smartmanagertwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CaiDatThongTinAppMain extends Fragment {
    Button btnChinhSach;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_cai_dat_thong_tin_app_main,container,false);
        btnChinhSach=root.findViewById(R.id.btnChinhSachBaoMat);
        addEvent();
        return root;
    }

    private void addEvent() {
        btnChinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaiDatThongTinAppChinhSach.class);
                startActivity(intent);
            }
        });
    }


}