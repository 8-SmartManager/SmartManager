package com.example.KeHoachMuaSam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.TaiKhoanChiTietActivity;
import com.example.taikhoan.TaiKhoanActivity;
import com.example.taikhoan.TaiKhoanAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class KeHoachMuaSamMain extends Fragment {

    public static final String TAG ="DanhSachMuaSam";

    public static MyDatabaseHelper db;

   ListView lvDanhSach;
    FloatingActionButton btnThemDanhSach;
    ListAdapter adapter;
    ArrayList<ListData> listDatas;
    ItemList selectedItem=null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_kehoachmuasam_main,container,false);
        prepareDB();
        lvDanhSach=root.findViewById(R.id.lvDanhSach);
        btnThemDanhSach=root.findViewById(R.id.btnThemDanhSach);

        addEvent();
        return root;
    }



    private void addEvent() {
        btnThemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachMuaSamThem.class);
                startActivity(intent);

            }
        });
    }

    private void prepareDB() {
        db = new MyDatabaseHelper(getContext());
        db.createSomeDanhSachData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter = new ListAdapter(getActivity(),getDataFromDb(),R.layout.item_dsmuasam);
        lvDanhSach.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ListData> getDataFromDb(){
        listDatas = new ArrayList<>();
//        Cursor cursor = db.getData("SELECT "+MyDatabaseHelper.COL_DANHSACH_ID+", "+MyDatabaseHelper.COL_DANHSACH_NAME+", 3, 4"+", (SELECT SUM("+MyDatabaseHelper.COL_DANHSACHITEM_PRICE+") FROM "+MyDatabaseHelper.TBL_NAME_DANHSACHITEM+") FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH);
        Cursor cursor = db.getData("SELECT * FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH);

        listDatas.clear();
        while (cursor.moveToNext()){
            listDatas.add(new ListData(cursor.getInt(0),cursor.getString(1),3, 4, cursor.getDouble(2)));
        }
        cursor.close();
        return listDatas;
    }


    private void addEvents() {
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DanhSachMuaSamChiTiet.class);
                adapter = new ListAdapter(getActivity(),listDatas, R.layout.item_dsmuasam);
                ListData list= (ListData) adapter.getItem(i);
                intent.putExtra("tlName",list);
                startActivity(intent);
            }
        });
    }

}
