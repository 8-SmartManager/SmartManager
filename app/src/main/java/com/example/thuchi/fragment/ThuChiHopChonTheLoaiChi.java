package com.example.thuchi.fragment;

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
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class ThuChiHopChonTheLoaiChi extends DialogFragment {

    GridView gvThuChiTaiKhoan;
    ArrayList<HopChonItem> items;
    HopChonAdapter adapter;
    ImageButton btnDismiss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvThuChiTaiKhoan=view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        adapter = new HopChonAdapter(getContext(),R.layout.hop_chon_item_co_hinh,initData());
        gvThuChiTaiKhoan.setAdapter(adapter);
       gvThuChiTaiKhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               TextView txtTheLoai= getActivity().findViewById(R.id.txtNewActivityName);
               adapter = new HopChonAdapter(getContext(),R.layout.hop_chon_item_co_hinh,initData());
               HopChonItem hopChonItem= (HopChonItem) adapter.getItem(i);

               txtTheLoai.setText(hopChonItem.getItemName());
               dismiss();
           }
       });
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        addEvents();
        return view;
    }

    private void addEvents() {
    }

    private ArrayList<HopChonItem> initData() {
        items = new ArrayList<HopChonItem>();
        items.add(new HopChonItem(R.drawable.ic_thu_nhap_the_loai_nhac_nho, "Giải trí"));
        items.add(new HopChonItem(R.drawable.ic_di_chuyen_the_loai_nhac_nho, "Ăn uống"));
        items.add(new HopChonItem(R.drawable.ic_quan__ao_the_loai_nhac_nho, "Sở thích"));
        items.add(new HopChonItem(R.drawable.ic_mua_sam_the_loai_nhac_nho, "Giáo dục"));
        items.add(new HopChonItem(R.drawable.ic_thu_nhap_the_loai_nhac_nho, "Sức khỏe"));
        items.add(new HopChonItem(R.drawable.ic_di_chuyen_the_loai_nhac_nho, "Sinh Hoạt"));
        items.add(new HopChonItem(R.drawable.ic_quan__ao_the_loai_nhac_nho, "Áo quần"));
        items.add(new HopChonItem(R.drawable.ic_mua_sam_the_loai_nhac_nho, "Làm đẹp"));
        items.add(new HopChonItem(R.drawable.ic_thu_nhap_the_loai_nhac_nho, "Khác"));
        return items;
    }
}
