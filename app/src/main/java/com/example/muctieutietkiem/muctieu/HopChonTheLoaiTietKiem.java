package com.example.muctieutietkiem.muctieu;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.muctieutietkiem.muctieu.adapter.TheLoaiMucTieuAdapter;
import com.example.muctieutietkiem.muctieu.model.TheLoai;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonTheLoaiTietKiem extends DialogFragment {
    GridView gvTheLoai;
    ArrayList<TheLoai> theLoai;
    TheLoaiMucTieuAdapter adapter;
    ImageButton btnDismiss;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.hop_chon_tietkiem, container, false);
        gvTheLoai=view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        EditText edtTenMucTieu = view.findViewById(R.id.edtTenMucTieu);
        adapter = new TheLoaiMucTieuAdapter(getContext(),R.layout.item_theloai_muctieu,initData());
        gvTheLoai.setAdapter(adapter);
        gvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                EditText edtTenMucTieu= getActivity().findViewById(R.id.edtTenMucTieu);
                adapter = new TheLoaiMucTieuAdapter(getContext(),R.layout.item_theloai_muctieu,initData());
                TheLoai theLoai= (TheLoai) adapter.getItem(i);
                edtTenMucTieu.setText(theLoai.getTenTheLoai());
            }
        });
        return view;
    }
    private ArrayList<TheLoai> initData() {
        theLoai=new ArrayList<TheLoai>();
        theLoai.add(new TheLoai("Mua nhà",R.drawable.ic_nha));
        theLoai.add(new TheLoai("Mua xe",R.drawable.ic_xe));
        theLoai.add(new TheLoai("Du lịch",R.drawable.ic_dullich));
        theLoai.add(new TheLoai("Học tập",R.drawable.ic_hoctap));
        theLoai.add(new TheLoai("Sức khỏe",R.drawable.ic_suc_khoe_1));
        theLoai.add(new TheLoai("Con cái",R.drawable.ic_concai));
        theLoai.add(new TheLoai("Kết hôn",R.drawable.ic_kethon));
        theLoai.add(new TheLoai("Bố mẹ",R.drawable.ic_bame));

        return theLoai;
    }
}
