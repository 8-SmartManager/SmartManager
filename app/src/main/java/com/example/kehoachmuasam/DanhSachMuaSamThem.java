package com.example.kehoachmuasam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

public class DanhSachMuaSamThem extends AppCompatActivity {

    Button btnTaoDanhSach;
    EditText edtNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mua_sam_them);

        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Danh Sách Mua Sắm Mới");

        linkViews();
        addEvents();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                    if(DanhSachMuaSamMain.listDatas.size()==0){
                        DanhSachMuaSamMain.db.execSql("INSERT INTO " +MyDatabaseHelper.TBL_NAME_DANHSACH + " VALUES(null,'"+name+"')");
                        Intent intent = new Intent(DanhSachMuaSamThem.this, DanhSachMuaSamChiTiet.class);
                        intent.putExtra("tlName",edtNhap.getText().toString());
                        startActivity(intent);
                        finish();
                    }else {
                    for (ListData l: DanhSachMuaSamMain.listDatas
                         ) {
                        if(l.getListTitle().equals(name)){
                            Dialog dialog = new Dialog(DanhSachMuaSamThem.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                            dialog.setContentView(R.layout.dialog_thong_bao);
                            TextView txtTitle=dialog.findViewById(R.id.txtTitle),
                                    txtMessage=dialog.findViewById(R.id.txtMessage);
                            Button btnYes=dialog.findViewById(R.id.btnYes),
                                    btnNo=dialog.findViewById(R.id.btnNo);
                            txtTitle.setText("Thông báo");
                            txtMessage.setText("Tên danh sách đã tồn tại. Bạn có muốn thay thế?");
                            btnYes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    DanhSachMuaSamMain.db.execSql("DELETE FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH+" WHERE "+MyDatabaseHelper.COL_DANHSACH_NAME+"='"+name+"'");
                                    DanhSachMuaSamMain.db.execSql("DELETE FROM "+MyDatabaseHelper.TBL_NAME_DANHSACHITEM+" WHERE "+MyDatabaseHelper.COL_DANHSACHITEM_DANHSACHNAME+"='"+name+"'");
                                    DanhSachMuaSamMain.db.execSql("INSERT INTO " +MyDatabaseHelper.TBL_NAME_DANHSACH + " VALUES(null,'"+name+"')");

                                    Intent intent = new Intent(DanhSachMuaSamThem.this, DanhSachMuaSamChiTiet.class);
                                    intent.putExtra("tlName",edtNhap.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            btnNo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }else {
                            DanhSachMuaSamMain.db.execSql("INSERT INTO " +MyDatabaseHelper.TBL_NAME_DANHSACH + " VALUES(null,'"+name+"')");
                            Intent intent = new Intent(DanhSachMuaSamThem.this, DanhSachMuaSamChiTiet.class);
                            intent.putExtra("tlName",edtNhap.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    }



                }
            }}
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