package com.example.KeHoachMuaSam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

public class DanhSachMuaSamThem extends AppCompatActivity {

    Button btnTaoDanhSach;
    EditText edtNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mua_sam_them);

        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Danh Sách Mua Sắm Mới");

        linkViews();
        addEvents();
    }

    private void linkViews() {

        btnTaoDanhSach=findViewById(R.id.btnTaoDanhSach);
        edtNhap=findViewById(R.id.edtNhap);
    }

    private void addEvents() {

        btnTaoDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNhap.getText().length()==0)
                {
                    ShowDialog();
                }
                else {
                    String name=edtNhap.getText().toString();
                    KeHoachMuaSamMain.db.execSql("INSERT INTO " +MyDatabaseHelper.TBL_NAME_DANHSACH + " VALUES(null,'"+name+"', '5000000')");


                    Intent intent = new Intent(DanhSachMuaSamThem.this, DanhSachMuaSamChiTiet.class);
                    intent.putExtra("tlName",edtNhap.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    private void ShowDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_nhapdanhsach);
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