package com.example.caidat.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caidat.CaiDatTroGiupNew;
import com.example.smartmanagertwo.R;

public class CaiDatTroGiup extends Fragment {

    TextView txtNew;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_cai_dat_tro_giup,container,false);
        txtNew = root.findViewById(R.id.txtNew);

        addEvents();
        return root;
    }
    private void addEvents() {
        txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaiDatTroGiupNew.class);
                startActivity(intent);
            }
        });
    }
}