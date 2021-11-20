package com.example.muctieutietkiem.packages;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.muctieutietkiem.packages.adapter.GoalAdapter;
import com.example.muctieutietkiem.packages.adapter.TheLoaiMucTieuAdapter;
import com.example.muctieutietkiem.packages.model.Goal;
import com.example.muctieutietkiem.packages.model.TheLoai;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class TaoMucTieu extends AppCompatActivity {
    EditText edtTenMucTieu;
    Button btnTaoMucTieu;
    GridView gvTheLoai;
    ArrayList<TheLoai> theLoai;
    TheLoaiMucTieuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_the_loai_muctieu);
        LinkView();
        initData();
        addEvents();
    }

    private void addEvents() {
        gvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtTenMucTieu.setText(theLoai.get(i).getTenTheLoai());
            }
        });
        btnTaoMucTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTenMucTieu.getText().length()==0)
                {
                    ShowDialog();
                }
                else {
                    Intent intent= new Intent(TaoMucTieu.this,TaoMucTieuChiTiet.class);
                    intent.putExtra("tlName",edtTenMucTieu.getText().toString());
                    startActivity(intent);

                }
            }
        });

    }

    private void initData() {
        theLoai= new ArrayList<>();

        theLoai.add(new TheLoai("Mua nhà",R.drawable.ic_nha));
        theLoai.add(new TheLoai("Mua xe",R.drawable.ic_xe));
        theLoai.add(new TheLoai("Du lịch",R.drawable.ic_dullich));
        theLoai.add(new TheLoai("Học tập",R.drawable.ic_hoctap));
        theLoai.add(new TheLoai("Sức khỏe",R.drawable.ic_suc_khoe_1));
        theLoai.add(new TheLoai("Con cái",R.drawable.ic_concai));
        theLoai.add(new TheLoai("Kết hôn",R.drawable.ic_kethon));
        theLoai.add(new TheLoai("Bố mẹ",R.drawable.ic_bame));

        adapter = new TheLoaiMucTieuAdapter(this, R.layout.fragment_the_loai,theLoai);
        gvTheLoai.setAdapter(adapter);



    }

    private void LinkView() {
        edtTenMucTieu= findViewById(R.id.edtTenMucTieu);
        btnTaoMucTieu=findViewById(R.id.btnTaoMucTieu);
        gvTheLoai=findViewById(R.id.gvTheLoai);


    }

    private void ShowDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.nhaptenmuctieu_dialog);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
