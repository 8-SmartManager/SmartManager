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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_the_loai_muctieu);
        LinkView();
        initData();
        addEvents();
    }

    private void addEvents() {


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



    }

    private void LinkView() {
        edtTenMucTieu= findViewById(R.id.edtTenMucTieu);
        btnTaoMucTieu=findViewById(R.id.btnTaoMucTieu);


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
