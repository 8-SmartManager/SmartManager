package com.example.caidatmatkhau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartmanagertwo.R;

public class CaiDatMatKhauMain extends Fragment {
    TextView txtChange, txtOff;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_cai_dat_mat_khau_main,container,false);
        txtOff= root.findViewById(R.id.txtOff);
        txtChange= root.findViewById(R.id.txtChange);
        addEvents();
        return root;
    }

    private void addEvents() {
        txtOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MatKhauOff.class);
                startActivity(intent);

            }
        });
        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MatKhauChange.class);
                startActivity(intent);
            }
        });
    }


}