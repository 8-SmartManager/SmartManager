package com.example.thongke.fragment;

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
import androidx.fragment.app.DialogFragment;

import com.example.hopchoncohinh.HopChonAdapter;
import com.example.hopchoncohinh.HopChonItem;
import com.example.hopchonkhonghinh.HopChonKhongHinhAdapter;
import com.example.hopchonkhonghinh.HopChonKhongHinhItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonTheLoaiThu extends DialogFragment {
    GridView gvThongKeTheLoai;
    ArrayList<HopChonKhongHinhItem> items;
    HopChonKhongHinhAdapter adapter;
    ImageButton btnDismiss;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvThongKeTheLoai = view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initData());
        gvThongKeTheLoai.setAdapter(adapter);
        gvThongKeTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtTheLoai = getActivity().findViewById(R.id.txtTKTheLoai);
                adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initData());
                HopChonKhongHinhItem hopChonKhongHinhItem = (HopChonKhongHinhItem) adapter.getItem(i);
                txtTheLoai.setText(hopChonKhongHinhItem.getName());
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
        items.add(new HopChonKhongHinhItem("Trả thêm giờ"));
        items.add(new HopChonKhongHinhItem("Tiền lương"));
        items.add(new HopChonKhongHinhItem("Tiền trợ cấp"));
        items.add(new HopChonKhongHinhItem("Tiền thưởng"));
        items.add(new HopChonKhongHinhItem("Khác"));
        return items;
    }
}
