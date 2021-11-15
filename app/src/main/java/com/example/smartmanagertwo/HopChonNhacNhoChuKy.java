package com.example.smartmanagertwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.hopchonkhonghinh.HopChonKhongHinhAdapter;
import com.example.hopchonkhonghinh.HopChonKhongHinhItem;

import java.util.ArrayList;

public class HopChonNhacNhoChuKy extends DialogFragment {
    GridView gvNhacNhoTheLoai;
    ArrayList<HopChonKhongHinhItem> items;
    HopChonKhongHinhAdapter adapter;
    TextView txtTieuDe;
    ImageButton btnDismiss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvNhacNhoTheLoai=view.findViewById(R.id.gvHopChonItem);
        txtTieuDe= view.findViewById(R.id.txtTieuDe);
        btnDismiss = view.findViewById(R.id.btnDismiss);

            txtTieuDe.setText("Chu kỳ");
            adapter = new HopChonKhongHinhAdapter(getContext(),R.layout.hop_chon_item_khong_hinh,initData());
            gvNhacNhoTheLoai.setAdapter(adapter);
            gvNhacNhoTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView txtChuKy= getActivity().findViewById(R.id.txtNhacNhoThemChuKy);
                    adapter = new HopChonKhongHinhAdapter(getContext(),R.layout.hop_chon_item_khong_hinh,initData());
                    HopChonKhongHinhItem hopChonItem= (HopChonKhongHinhItem) adapter.getItem(i);

                    txtChuKy.setText(hopChonItem.getName());dismiss();

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
        items.add(new HopChonKhongHinhItem( "Một lần"));
        items.add(new HopChonKhongHinhItem( "Hàng ngày"));
        items.add(new HopChonKhongHinhItem( "Hàng tuần"));
        items.add(new HopChonKhongHinhItem( "Hàng tháng"));
        items.add(new HopChonKhongHinhItem( "Hàng năm"));

        return items;

    }

}
