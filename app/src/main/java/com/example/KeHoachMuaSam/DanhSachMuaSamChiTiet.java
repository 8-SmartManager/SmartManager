package com.example.KeHoachMuaSam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.KeHoachMuaSam.adapter.ItemDapter;
import com.example.KeHoachMuaSam.model.DanhSachItem;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachMuaSamChiTiet extends AppCompatActivity {


     FloatingActionButton fabThem;
    ListData selectedList;
     ListView lvDanhSachItem;
     String tenDanhSach;
     ItemDapter adapter;
     ArrayList<DanhSachItem> tasks;

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
        tasks=new ArrayList<>();
        Cursor cursor = KeHoachMuaSamMain.db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_DANHSACHITEM);
        tasks.clear();
        while (cursor.moveToNext()){
            tasks.add(new DanhSachItem(cursor.getInt(0),cursor.getString(1), cursor.getDouble(2),cursor.getInt(3)));
        }
        cursor.close();
        return tasks;
    }

    private void addEvents() {
        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }

            private void openAddDialog() {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog_add_danhsach, null);
                final EditText edtName = alertLayout.findViewById(R.id.edtName);
                final EditText edtPrice = alertLayout.findViewById(R.id.edtPrice);

                AlertDialog.Builder alert = new AlertDialog.Builder(DanhSachMuaSamChiTiet.this);
                alert.setTitle("Thêm Item");
                alert.setView(alertLayout);
                alert.setCancelable(false);
                alert.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String itemName= edtName.getText().toString();
                        String itemPrice=edtPrice.getText().toString();
                        if(itemName.equals("")&& itemPrice.equals("")){
                            Toast.makeText(DanhSachMuaSamChiTiet.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            KeHoachMuaSamMain.db.execSql("INSERT INTO " +MyDatabaseHelper.TBL_NAME_DANHSACHITEM + " VALUES(null,'"+itemName+"', '"+itemPrice+"',0)");
                            Toast.makeText(DanhSachMuaSamChiTiet.this, "Success!", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                            loadData();
                        }
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;}
        return super.onOptionsItemSelected(item);
    }
    public void openEditDialog(DanhSachItem t){
        //Toast.makeText(this, "t.getTaskName", Toast.LENGTH_SHORT).show();
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_danhsachmuasam);
        EditText edtName=dialog.findViewById(R.id.edtName);
        EditText edtPrice=dialog.findViewById(R.id.edtPrice);

        edtName.setText(t.getItemName());
        edtPrice.setText(String.format("%,.0f", t.getItemPrice()));
        Button btnOK= dialog.findViewById(R.id.btnOK);
//        btnOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String taskName=edtName.getText().toString();
//                String taskPrice=edtPrice.getText().toString();
////                db.execSql("UPDATE " + MyDatabaseHelper.TBL_NAME_DANHSACH + " SET " + MyDatabaseHelper.COL_DANHSACH_NAME + " = '" + taskName + MyDatabaseHelper.COL_DANHSACH_PRICE + taskPrice + "' " +
////                        "' WHERE " + MyDatabaseHelper.COL_DANHSACH_ID + " = "  + t.getTaskId());
//                db.execSql("UPDATE "+MyDatabaseHelper.TBL_NAME_DANHSACH + " SET " + MyDatabaseHelper.COL_NHACNHO_NAME +" = '"+taskName+"', "+MyDatabaseHelper.COL_DANHSACH_PRICE+" = '"+taskPrice+"' " +
//                        " 'WHERE "+MyDatabaseHelper.COL_DANHSACH_ID+"=" +t.getTaskId());
//                Toast.makeText(DanhSachMuaSamChiTiet.this, "Success!", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//                loadData();
//            }
//        });
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
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setTitle("Confirm!");
    builder.setMessage("Are you sure you want to delete this item: " + t.getItemName()+ "?");
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            KeHoachMuaSamMain.db.execSql("DELETE FROM " + MyDatabaseHelper.TBL_NAME_DANHSACHITEM + " WHERE " + MyDatabaseHelper.COL_DANHSACHITEM_ID + " = " + t.getItemId());
            Toast.makeText(DanhSachMuaSamChiTiet.this, "Success!", Toast.LENGTH_SHORT).show();
            loadData();
        }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    });
    builder.create().show();

}



            private void getData() {
                Intent intent = getIntent();
                tenDanhSach=  intent.getStringExtra("tlName");


            }
        }
