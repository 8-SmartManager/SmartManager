package com.example.KeHoachMuaSam;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.KeHoachMuaSam.model.DanhSachItem;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.TaiKhoanChiTietActivity;
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
    DanhSachItem selectedItem=null;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_danh_sach_mua_sam_main,container,false);
        prepareDB();
        lvDanhSach=root.findViewById(R.id.lvDanhSach);
        btnThemDanhSach=root.findViewById(R.id.btnThemDanhSach);

        addEvent();
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
//        Cursor cursor = db.getData("SELECT "+MyDatabaseHelper.COL_DANHSACH_ID+", "+MyDatabaseHelper.COL_DANHSACH_NAME+", 3, 4"+", (SELECT SUM("+MyDatabaseHelper.COL_DANHSACHITEM_PRICE+") FROM "+MyDatabaseHelper.TBL_NAME_DANHSACHITEM+") FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH);
        Cursor cursor = db.getData("SELECT * FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH);

        listDatas.clear();
        while (cursor.moveToNext()){
            listDatas.add(new ListData(cursor.getInt(0),cursor.getString(1),3, 4, cursor.getDouble(2)));
        }
        cursor.close();
        return listDatas;
    }


    private void addEvent() {
        btnThemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachMuaSamThem.class);
                startActivity(intent);

            }
        });
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
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Confirm!");
                builder.setMessage("Bạn có chắc chắn muốn xóa danh sách này?");
                builder.setIcon(android.R.drawable.ic_delete);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        KeHoachMuaSamMain.db.execSql("DELETE FROM "+MyDatabaseHelper.TBL_NAME_DANHSACH+" WHERE "+MyDatabaseHelper.COL_DANHSACH_ID + "=" +selectedItem.getItemId());

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog =builder.create();
                dialog.show();
                return false;
            }
        });


    }


}


