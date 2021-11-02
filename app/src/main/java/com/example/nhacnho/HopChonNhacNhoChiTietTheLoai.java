package com.example.nhacnho;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.hopchoncohinh.HopChonAdapter;
import com.example.hopchoncohinh.HopChonItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonNhacNhoChiTietTheLoai extends DialogFragment {
    GridView gvNhacNhoTheLoai;
    ArrayList<HopChonItem> items;
    HopChonAdapter adapter;
    ImageButton btnDismiss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvNhacNhoTheLoai=view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        adapter = new HopChonAdapter(getContext(),R.layout.hop_chon_item_co_hinh,initData());
        gvNhacNhoTheLoai.setAdapter(adapter);
        gvNhacNhoTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView txtTheLoai= getActivity().findViewById(R.id.txtNhacNhoChiTietTheLoai);
                adapter = new HopChonAdapter(getContext(),R.layout.hop_chon_item_co_hinh,initData());
                HopChonItem hopChonItem= (HopChonItem) adapter.getItem(i);

                txtTheLoai.setText(hopChonItem.getItemName());}

        }
        );
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        addEvents();
        return view;
    }

    private ArrayList<HopChonItem> initData() {
        items = new ArrayList<HopChonItem>();
        items.add(new HopChonItem(R.drawable.ic_thu_nhap_the_loai_nhac_nho, "Thu"));
        items.add(new HopChonItem(R.drawable.ic_di_chuyen_the_loai_nhac_nho, "Chi"));
        items.add(new HopChonItem(R.drawable.ic_quan__ao_the_loai_nhac_nho, "Tiết kiệm"));
        items.add(new HopChonItem(R.drawable.ic_mua_sam_the_loai_nhac_nho, "DS mua sắm"));
        return items;

    }



    private void addEvents() {
    }
}
