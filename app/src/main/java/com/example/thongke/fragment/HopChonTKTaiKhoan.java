package com.example.thongke.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.hopchonkhonghinh.HopChonKhongHinhAdapter;
import com.example.hopchonkhonghinh.HopChonKhongHinhItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonTKTaiKhoan extends DialogFragment {
    GridView gvThongKeTaiKhoan;
    ArrayList<HopChonKhongHinhItem> items;
    HopChonKhongHinhAdapter adapter;
    ImageButton btnDismiss;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvThongKeTaiKhoan = view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initData());
        gvThongKeTaiKhoan.setAdapter(adapter);
        gvThongKeTaiKhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtTaiKhoan = getActivity().findViewById(R.id.txtTKTaiKhoan);
                adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initData());
                HopChonKhongHinhItem hopChonKhongHinhItem = (HopChonKhongHinhItem) adapter.getItem(i);
                txtTaiKhoan.setText(hopChonKhongHinhItem.getName());
                dismiss();
            }
        });
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    private ArrayList<HopChonKhongHinhItem> initData() {
        items = new ArrayList<HopChonKhongHinhItem>();
        items.add(new HopChonKhongHinhItem("Tiền mặt"));
        items.add(new HopChonKhongHinhItem("Tài khoản ngân hàng"));
        items.add(new HopChonKhongHinhItem("Thẻ tín dụng"));
        return items;
    }
}

