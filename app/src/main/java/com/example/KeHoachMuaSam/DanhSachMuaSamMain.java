package com.example.KeHoachMuaSam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMuaSamMain extends Fragment {

    public static final String TAG ="DanhSachMuaSam";

    public static MyDatabaseHelper db;

   ListView lvDanhSach;
    FloatingActionButton btnThemDanhSach;
    ListAdapter adapter;
    public  static ArrayList<ListData> listDatas;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_danh_sach_mua_sam_main,container,false);
        prepareDB();
        lvDanhSach=root.findViewById(R.id.lvDanhSach);
        btnThemDanhSach=root.findViewById(R.id.btnThemDanhSach);
        addEvents();
        return root;
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
        Cursor cursor = db.getData("SELECT "+ MyDatabaseHelper.COL_DANHSACH_ID+", "+MyDatabaseHelper.COL_DANHSACH_NAME+",0, COUNT(*), SUM("+MyDatabaseHelper.COL_DANHSACHITEM_PRICE+") FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH+" INNER JOIN "+MyDatabaseHelper.TBL_NAME_DANHSACHITEM+" ON "+MyDatabaseHelper.COL_DANHSACH_NAME+"="+MyDatabaseHelper.COL_DANHSACHITEM_DANHSACHNAME+" GROUP BY "+MyDatabaseHelper.COL_DANHSACH_NAME+", "+MyDatabaseHelper.COL_DANHSACH_ID);
        listDatas.clear();
        while (cursor.moveToNext()){
            listDatas.add(new ListData(cursor.getInt(0),cursor.getString(1),cursor.getInt(2), cursor.getInt(3), cursor.getDouble(4)));
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
                intent.putExtra("tlName",list.getListTitle());
                startActivity(intent);
            }
        });
        btnThemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachMuaSamThem.class);
                startActivity(intent);
            }
        });
    }

}
