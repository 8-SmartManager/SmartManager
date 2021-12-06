package com.example.KeHoachMuaSam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.KeHoachMuaSam.adapter.TaskAdapter;
import com.example.KeHoachMuaSam.model.Task;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachMuaSamChiTiet extends AppCompatActivity {

     MyDatabaseHelper db;
     FloatingActionButton fabThem;
     TextView txtTenDanhSach;
     Button btnBack;
     ListView lvDanhSachItem;
     String tenDanhSach;
     TaskAdapter adapter;
     ArrayList<Task> tasks;


     Intent intent;

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
        addEvents();
        prepareDb();
        loadData();

    }



    private void linkView() {
        fabThem=findViewById(R.id.fabThem);
        lvDanhSachItem=findViewById(R.id.lvDanhSachItem);

    }
    private void loadData() {

//        adapter =new TaskAdapter(DanhSachMuaSamChiTiet.this,R.layout.item_list_ds_mua_sam,getDataFromDb());
//        rcvDanhSachItem.setAdapter(adapter);
    }
    private void prepareDb() {
//        db = new MyDatabaseHelper(this);
//        db.createSomeDanhSachData();
    }

    private ArrayList<Task> getDataFromDb(){
//        tasks=new ArrayList<>();
//        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_DANHSACH);
//        tasks.clear();
//        while (cursor.moveToNext()){
//            tasks.add(new Task(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2)));
//        }
//        cursor.close();
        return null;
    }

    private void addEvents() {
        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }

            private void openAddDialog() {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_add_danhsach, null);
                final EditText edtName = alertLayout.findViewById(R.id.edtName);
                final EditText edtPrice = alertLayout.findViewById(R.id.edtPrice);

                AlertDialog.Builder alert = new AlertDialog.Builder(DanhSachMuaSamChiTiet.this);
                alert.setTitle("Thêm Item");
                alert.setView(alertLayout);
                alert.setCancelable(false);
                alert.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

            }

            private void getData() {
                intent = getIntent();
                tenDanhSach = intent.getStringExtra("tlName");

            }
        }
