package com.example.KeHoachMuaSam;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KeHoachMuaSamMain extends AppCompatActivity {

    public static final String TAG ="DanhSachMuaSam";

    public static SQLiteDatabase db;

    RecyclerView rcvDanhSach;
    FloatingActionButton fabThemDanhSach;
    ArrayAdapter<ItemList> adapter;
    ArrayList<ItemList> items;
    ItemList selectedItem=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kehoachmuasam_main);

        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Danh Sách Mua Sắm");

        linkView();
        addEvent();
    }

    private void linkView() {
        rcvDanhSach=findViewById(R.id.rcvDanhSach);
        fabThemDanhSach=findViewById(R.id.fabThemDanhSach);
    }

    private void addEvent() {
        fabThemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KeHoachMuaSamMain.this, DanhSachMuaSamThem.class);
                startActivity(intent);
                finish();
            }
        });
//        rcvDanhSach.setOnClickListener(new AdapterView.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectedItem=adapter.getItem(i);
//                return false;
//            }
//        });
    }

//    private ArrayList<ItemList> initData(){
//        items=new ArrayList<>();
//        db=openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
//        Cursor cursor = db.rawQuery("SELECT * FROM ItemList",null);
//
//        ItemList l;
//        while (cursor.moveToNext()){
//            l=new ItemList(cursor.getString(0),cursor.getDouble(1));
//            items.add(l);
//        }
//        cursor.close();
//        return items;
//    }
}
