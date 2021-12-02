package com.example.thongke;

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

public class HopChonTKTheLoai extends DialogFragment {
    GridView gvThongKeTheLoai;
    ArrayList<HopChonItem> items;
    HopChonAdapter adapter;
    ImageButton btnDismiss;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvThongKeTheLoai = view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        adapter = new HopChonAdapter(getContext(), R.layout.hop_chon_item_co_hinh, initData());
        gvThongKeTheLoai.setAdapter(adapter);
        gvThongKeTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtTheLoai = getActivity().findViewById(R.id.txtTKTheLoai);
                adapter = new HopChonAdapter(getContext(), R.layout.hop_chon_item_co_hinh, initData());
                HopChonItem hopChonItem = (HopChonItem) adapter.getItem(i);
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
        return view;
    }

    private ArrayList<HopChonItem> initData() {
        items = new ArrayList<HopChonItem>();
        items.add(new HopChonItem(R.drawable.ic_thu_nhap_the_loai_nhac_nho, "Ăn uống"));
        items.add(new HopChonItem(R.drawable.ic_giai_tri, "Giải trí"));
        items.add(new HopChonItem(R.drawable.ic_lam_dep, "Làm đẹp"));
        items.add(new HopChonItem(R.drawable.ic_hoctap, "Học tập"));
        return items;
    }
}
