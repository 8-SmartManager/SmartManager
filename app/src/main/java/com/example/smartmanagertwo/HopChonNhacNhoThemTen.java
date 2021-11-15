package com.example.smartmanagertwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.hopchoncohinh.HopChonAdapter;
import com.example.hopchoncohinh.HopChonItem;
import com.example.hopchonkhonghinh.HopChonKhongHinhAdapter;
import com.example.hopchonkhonghinh.HopChonKhongHinhItem;

import java.util.ArrayList;

public class HopChonNhacNhoThemTen extends DialogFragment {
    GridView gvNhacNhoTheLoai;
    ArrayList<HopChonKhongHinhItem> items;
    HopChonKhongHinhAdapter adapter;
    EditText edtTen;
    TextView txtTieuDe, txtTheLoaiThem, txtTheLoaiChiTiet;
    ImageButton btnDismiss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvNhacNhoTheLoai=view.findViewById(R.id.gvHopChonItem);
        txtTieuDe= view.findViewById(R.id.txtTieuDe);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        edtTen=view.findViewById(R.id.edtNhacNhoChiTietTen);
        txtTheLoaiThem = getActivity().findViewById(R.id.txtNhacNhoThemTheLoai);
        txtTheLoaiChiTiet= getActivity().findViewById(R.id.txtNhacNhoChiTietTheLoai);
        if (view.getId()==R.id.edtNhacNhoChiTietTen){
            edtTen.setFocusable(true);
            edtTen.setFocusableInTouchMode(true);

        }
        if(txtTheLoaiThem.getText()=="Thu")
        {
            txtTieuDe.setText("Thu");
            adapter = new HopChonKhongHinhAdapter(getContext(),R.layout.hop_chon_item_khong_hinh,initDataThu());
            gvNhacNhoTheLoai.setAdapter(adapter);
            gvNhacNhoTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView txtTen= getActivity().findViewById(R.id.txtNhacNhoThemTen);

                    adapter = new HopChonKhongHinhAdapter(getContext(),R.layout.hop_chon_item_khong_hinh,initDataThu());
                    HopChonKhongHinhItem hopChonItem= (HopChonKhongHinhItem) adapter.getItem(i);

                    txtTen.setText(hopChonItem.getName());
                    dismiss();
                }
            });
        }
        if(txtTheLoaiThem.getText()=="Chi")
        {
            txtTieuDe.setText("Chi");
            adapter = new HopChonKhongHinhAdapter(getContext(),R.layout.hop_chon_item_khong_hinh,initDataChi());
            gvNhacNhoTheLoai.setAdapter(adapter);
            gvNhacNhoTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView txtTen= getActivity().findViewById(R.id.txtNhacNhoThemTen);
                    adapter = new HopChonKhongHinhAdapter(getContext(),R.layout.hop_chon_item_khong_hinh,initDataChi());
                    HopChonKhongHinhItem hopChonItem= (HopChonKhongHinhItem) adapter.getItem(i);

                    txtTen.setText(hopChonItem.getName());
                    dismiss();
                }
            });
        }
        if(txtTheLoaiThem.getText()=="Tiết kiệm") {
            txtTieuDe.setText("Tiết kiệm");
            adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initDataTietKiem());
            gvNhacNhoTheLoai.setAdapter(adapter);
            gvNhacNhoTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView txtTen = getActivity().findViewById(R.id.txtNhacNhoThemTen);
                    adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initDataTietKiem());
                    HopChonKhongHinhItem hopChonItem = (HopChonKhongHinhItem) adapter.getItem(i);
                    if( hopChonItem.getName()=="Khác")
                    {
                        txtTen.setEnabled(true);
                    }
                    else {
                    txtTen.setText(hopChonItem.getName());
                        dismiss();}
                }
            });

        }
            if (txtTheLoaiThem.getText() == "DS mua sắm" ) {
                txtTieuDe.setText("DS mua sắm");
                adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initDataDsMuaSam());
                gvNhacNhoTheLoai.setAdapter(adapter);
                gvNhacNhoTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView txtTen = getActivity().findViewById(R.id.txtNhacNhoThemTen);
                        adapter = new HopChonKhongHinhAdapter(getContext(), R.layout.hop_chon_item_khong_hinh, initDataDsMuaSam());
                        HopChonKhongHinhItem hopChonItem = (HopChonKhongHinhItem) adapter.getItem(i);

                        txtTen.setText(hopChonItem.getName());
                        dismiss();
                    }
                });


            }


        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        addEvents();
        return view;
    }

    private ArrayList<HopChonKhongHinhItem> initDataTietKiem() {
        items = new ArrayList<HopChonKhongHinhItem>();
        items.add(new HopChonKhongHinhItem( "Mua nhà"));
        items.add(new HopChonKhongHinhItem( "Mua xe"));
        items.add(new HopChonKhongHinhItem( "Hôn nhân"));
        items.add(new HopChonKhongHinhItem( "Du học"));
        items.add(new HopChonKhongHinhItem( "Khác"));

        return items;

    }
    private ArrayList<HopChonKhongHinhItem> initDataThu() {
        items = new ArrayList<HopChonKhongHinhItem>();
        items.add(new HopChonKhongHinhItem( "Tiền lương"));
        items.add(new HopChonKhongHinhItem( "Tiền trợ cấp"));
        items.add(new HopChonKhongHinhItem( "Tiền"));

        return items;

    }
    private ArrayList<HopChonKhongHinhItem> initDataChi() {
        items = new ArrayList<HopChonKhongHinhItem>();
        items.add(new HopChonKhongHinhItem( "Ăn uống"));
        items.add(new HopChonKhongHinhItem( "Giải trí"));
        items.add(new HopChonKhongHinhItem( "Giáo dục"));
        items.add(new HopChonKhongHinhItem( "Sở thích"));
        items.add(new HopChonKhongHinhItem( "Sức khỏe"));
        items.add(new HopChonKhongHinhItem( "Sinh hoạt"));
        items.add(new HopChonKhongHinhItem( "Áo quần"));
        items.add(new HopChonKhongHinhItem( "Làm đẹp"));
        return items;

    }

    private ArrayList<HopChonKhongHinhItem> initDataDsMuaSam() {
        items = new ArrayList<HopChonKhongHinhItem>();
        items.add(new HopChonKhongHinhItem( "Shopping"));
        items.add(new HopChonKhongHinhItem( "Ăn uống"));

        return items;

    }


    private void addEvents() {
    }
}
