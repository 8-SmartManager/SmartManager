package com.example.KeHoachMuaSam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.hopchoncohinh.HopChonAdapter;
import com.example.hopchoncohinh.HopChonItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonTheLoai extends DialogFragment {
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

                TextView txtTheLoai= getActivity().findViewById(R.id.txtTheLoai);
                adapter = new HopChonAdapter(getContext(),R.layout.hop_chon_item_co_hinh,initData());
                HopChonItem hopChonItem= (HopChonItem) adapter.getItem(i);

                txtTheLoai.setText(hopChonItem.getItemName());
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
        addEvents();
        return view;
    }

    private ArrayList<HopChonItem> initData() {
        items = new ArrayList<HopChonItem>();
        items.add(new HopChonItem(R.drawable.ic_giai_tri_the_loai_chi, "Giải trí"));
        items.add(new HopChonItem(R.drawable.ic_thuc_pham_the_loai_chi, "Ăn uống"));
        items.add(new HopChonItem(R.drawable.ic_so_thich_the_loai_chi, "Sở thích"));
        items.add(new HopChonItem(R.drawable.ic_suc_khoe_1, "Sức khỏe"));
        items.add(new HopChonItem(R.drawable.ic_di_chuyen_the_loai_chi, "Sinh hoạt"));
        items.add(new HopChonItem(R.drawable.ic_quan_ao_the_loai_chi, "Áo quần"));
        items.add(new HopChonItem(R.drawable.ic_lam_dep_the_loai_chi, "Làm đẹp"));
        items.add(new HopChonItem(R.drawable.ic_khac, "Khác"));
        return items;

    }



    private void addEvents() {
    }
}
