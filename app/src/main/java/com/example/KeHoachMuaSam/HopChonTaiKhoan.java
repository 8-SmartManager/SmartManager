package com.example.KeHoachMuaSam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.hopchonkhonghinh.HopChonKhongHinhAdapter;
import com.example.hopchonkhonghinh.HopChonKhongHinhItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonTaiKhoan extends DialogFragment {
    GridView gvNhacNhoTheLoai;
    ArrayList<HopChonKhongHinhItem> items;
    HopChonKhongHinhAdapter adapter;
    ImageButton btnDismiss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvNhacNhoTheLoai=view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        adapter = new HopChonKhongHinhAdapter(getContext(),R.layout.hop_chon_item_khong_hinh,initData());
        gvNhacNhoTheLoai.setAdapter(adapter);
        gvNhacNhoTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView txtTaiKhoan = getActivity().findViewById(R.id.txtTaiKhoan);
                adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initData());
                HopChonKhongHinhItem hopChonItem = (HopChonKhongHinhItem) adapter.getItem(i);

                txtTaiKhoan.setText(hopChonItem.getName());
                dismiss();
            }

        }
        );
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
        items.add(new HopChonKhongHinhItem( "Tiền mặt"));
        items.add(new HopChonKhongHinhItem( "Tài khoản NH"));
        items.add(new HopChonKhongHinhItem( "Thẻ tín dụng"));

        return items;

    }

}
