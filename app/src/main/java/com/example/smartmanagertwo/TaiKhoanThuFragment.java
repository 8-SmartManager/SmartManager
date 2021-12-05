package com.example.smartmanagertwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import androidx.fragment.app.Fragment;

public class TaiKhoanThuFragment extends Fragment {
    TableRow tbrTienMat;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tai_khoan_chinh, container, false);

        tbrTienMat = view.findViewById(R.id.tbrow);

        addEvents();

        return view;
    }

    private void addEvents() {
        tbrTienMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),TaiKhoanChiTiet.class);
                startActivity(intent);
            }
        });
    }


}
