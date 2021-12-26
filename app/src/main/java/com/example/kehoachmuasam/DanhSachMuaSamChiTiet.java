package com.example.kehoachmuasam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kehoachmuasam.adapter.ItemDapter;
import com.example.kehoachmuasam.model.DanhSachItem;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachMuaSamChiTiet extends AppCompatActivity {


    FloatingActionButton fabThem;
    ListView lvDanhSachItem;
    String tenDanhSach;
    ItemDapter adapter;
    ArrayList<DanhSachItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mua_sam_chi_tiet);
        getData();

        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle(tenDanhSach);

        linkView();
        loadData();
        addEvents();
    }

    private void linkView() {
        fabThem=findViewById(R.id.fabThem);
        lvDanhSachItem=findViewById(R.id.lvDanhSachItem);

    }
    private void loadData() {

        adapter =new ItemDapter(DanhSachMuaSamChiTiet.this,R.layout.item_list_ds_mua_sam,getDataFromDb());
        lvDanhSachItem.setAdapter(adapter);
    }


    private ArrayList<DanhSachItem> getDataFromDb(){
        items=new ArrayList<>();
        Cursor cursor = DanhSachMuaSamMain.db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_DANHSACHITEM+" WHERE "+MyDatabaseHelper.COL_DANHSACHITEM_DANHSACHNAME+"='"+tenDanhSach+"'");
        items.clear();
        while (cursor.moveToNext()){
            items.add(new DanhSachItem(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getDouble(3),cursor.getInt(4)));
        }
        cursor.close();
        return items;
    }

    private void addEvents() {
        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }

            private void openAddDialog() {
                Dialog dialog = new Dialog(DanhSachMuaSamChiTiet.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_add_danhsach);
                EditText edtName=dialog.findViewById(R.id.edtName),
                        edtPrice=dialog.findViewById(R.id.edtPrice);
                Button btnAdd=dialog.findViewById(R.id.btnAdd),
                        btnCancel=dialog.findViewById(R.id.btnCancel);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String itemName= edtName.getText().toString();
                        String itemPrice=edtPrice.getText().toString();
                        if(itemName.equals("")&& itemPrice.equals("")){
                            Toast.makeText(DanhSachMuaSamChiTiet.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            DanhSachMuaSamMain.db.execSql("INSERT INTO " +MyDatabaseHelper.TBL_NAME_DANHSACHITEM + " VALUES(null,'"+tenDanhSach+"','"+itemName+"', '"+itemPrice+"',0)");
                            Toast.makeText(DanhSachMuaSamChiTiet.this, "Success!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadData();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mua_sam_edit_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                if(items.size()==0){
                    Dialog dialogDone = new Dialog(DanhSachMuaSamChiTiet.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogDone.setContentView(R.layout.dialog_error);
                    TextView txtTitleDone=dialogDone.findViewById(R.id.txtTitle),
                            txtMessageDone=dialogDone.findViewById(R.id.txtMessage);
                    Button btnYesDone=dialogDone.findViewById(R.id.btnYes);

                    txtTitleDone.setText("Lỗi");
                    txtMessageDone.setText("Vui lòng thêm item danh sách");
                    btnYesDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogDone.dismiss();
                        }
                    });

                    dialogDone.show();
                }
                else {
                    onBackPressed();
                }
                break;
            case R.id.mnDelete:
                Dialog dialog = new Dialog(DanhSachMuaSamChiTiet.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_xoa);
                Button btnYes=dialog.findViewById(R.id.btnYes),
                        btnNo=dialog.findViewById(R.id.btnNo);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DanhSachMuaSamMain.db.execSql("DELETE FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH+" WHERE "+MyDatabaseHelper.COL_DANHSACH_NAME + "='" +tenDanhSach+"'");
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
                break;
            case R.id.mnDone:
                finish();
            default:break;}
        return super.onOptionsItemSelected(item);
    }
    public void openEditDialog(DanhSachItem t){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_danhsachmuasam);
        EditText edtName=dialog.findViewById(R.id.edtName);
        EditText edtPrice=dialog.findViewById(R.id.edtPrice);
        edtName.setText(t.getItemName());
        edtPrice.setText(String.format("%.0f", t.getItemPrice()));
        Button btnOK= dialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName=edtName.getText().toString();
                String itemPrice=edtPrice.getText().toString();
                if(itemName.equals("")||itemPrice.equals("")){
                    Dialog dialogDone = new Dialog(DanhSachMuaSamChiTiet.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialogDone.setContentView(R.layout.dialog_error);
                    TextView txtTitleDone=dialogDone.findViewById(R.id.txtTitle),
                            txtMessageDone=dialogDone.findViewById(R.id.txtMessage);
                    Button btnYesDone=dialogDone.findViewById(R.id.btnYes);
                    txtTitleDone.setText("Lỗi");
                    txtMessageDone.setText("Vui lòng nhập đầy đủ thông tin!");
                    btnYesDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogDone.dismiss();
                        }
                    });
                    dialogDone.show();
                }else {
                    DanhSachMuaSamMain.db.execSql("UPDATE " + MyDatabaseHelper.TBL_NAME_DANHSACHITEM + " SET " + MyDatabaseHelper.COL_DANHSACHITEM_NAME + " = '" + itemName + "', " + MyDatabaseHelper.COL_DANHSACHITEM_PRICE + " = " + itemPrice + " " +
                            " WHERE " + MyDatabaseHelper.COL_DANHSACHITEM_ID + "=" + t.getItemId());
                    Toast.makeText(DanhSachMuaSamChiTiet.this, "Success!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadData();
                }
            }
        });
        Button btnCancle=dialog.findViewById(R.id.btnCancel);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void  deleteTask (DanhSachItem t){
        Dialog dialog = new Dialog(DanhSachMuaSamChiTiet.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
        dialog.setContentView(R.layout.dialog_xoa);
        Button btnYes=dialog.findViewById(R.id.btnYes),
                btnNo=dialog.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachMuaSamMain.db.execSql("DELETE FROM " + MyDatabaseHelper.TBL_NAME_DANHSACHITEM + " WHERE " + MyDatabaseHelper.COL_DANHSACHITEM_ID + " = " + t.getItemId());
                Toast.makeText(DanhSachMuaSamChiTiet.this, "Success!", Toast.LENGTH_SHORT).show();
                loadData();
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void getData() {
        Intent intent = getIntent();
        tenDanhSach=  intent.getStringExtra("tlName");
    }
}