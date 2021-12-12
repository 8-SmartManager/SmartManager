package com.example.KeHoachMuaSam;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KeHoachMuaSamMain extends Fragment {

    public static final String TAG ="DanhSachMuaSam";

    public static SQLiteDatabase db;

    RecyclerView rcvDanhSach;
    FloatingActionButton fabThemDanhSach;
    ArrayAdapter<ItemList> adapter;
    ArrayList<ItemList> items;
    ItemList selectedItem=null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_kehoachmuasam_main,container,false);
        rcvDanhSach=root.findViewById(R.id.rcvDanhSach);
        fabThemDanhSach=root.findViewById(R.id.fabThemDanhSach);

        addEvent();
        return root;
    }



    private void addEvent() {
        fabThemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachMuaSamThem.class);
                startActivity(intent);
                getActivity().finish();
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
