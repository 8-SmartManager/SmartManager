package com.example.taikhoan.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.taikhoan.TaiKhoanChiTietActivity;
import com.example.thuchi.model.ThuChiActivity;
import com.example.taikhoan.model.TaiKhoanInfo;
import com.example.taikhoan.adapter.TaiKhoanAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoan extends Fragment {

    ListView lvTaiKhoanThu;
    TextView txtTongTK, txtNo, txtCong;
    ArrayList<TaiKhoanInfo> InfoTaiKhoanThu;
    TaiKhoanAdapter adapter;
    public static MyDatabaseHelper db;
    ArrayList<ThuChiActivity> arrThuChi;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_tai_khoan_chinh,container,false);
        prepareDB();
        lvTaiKhoanThu = root.findViewById(R.id.lvTaiKhoanThu);
        txtTongTK = root.findViewById(R.id.txtTongTK);
        txtCong=root.findViewById(R.id.txtCong);
        txtNo=root.findViewById(R.id.txtNo);

        addEvents();
        return root;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

    private void linkViews() {

    }
    private void prepareDB() {
        db = new MyDatabaseHelper(getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter = new TaiKhoanAdapter(getActivity(),R.layout.item_tai_khoan_layout,getDataFromDb());
        lvTaiKhoanThu.setAdapter(adapter);
        double taikhoan=0, no=0;
        for (ThuChiActivity thuchiac:getDataFromDb1()
        ) {
            if(thuchiac.getActivityType().equals("Thu")){
                taikhoan += thuchiac.getActivityAmount();
            }else {
                no += thuchiac.getActivityAmount();
            }


        }
        txtTongTK.setText(String.format("%,.0f",taikhoan));
        txtNo.setText(String.format("%,.0f",no));
        txtCong.setText(String.format("%,.0f",taikhoan - no));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<TaiKhoanInfo> getDataFromDb(){
        InfoTaiKhoanThu = new ArrayList<>();
        Cursor cursor = db.getData("SELECT "+MyDatabaseHelper.COL_THUCHI_ACCOUNT + " , " + " SUM(" +MyDatabaseHelper.COL_THUCHI_AMOUNT + " ) " + " FROM " + MyDatabaseHelper.TBL_NAME_THUCHI + " GROUP BY " + MyDatabaseHelper.COL_THUCHI_ACCOUNT);
        InfoTaiKhoanThu.clear();
        while (cursor.moveToNext()){
            InfoTaiKhoanThu.add(new TaiKhoanInfo(cursor.getString(0),cursor.getDouble(1)));
        }
        cursor.close();
        return InfoTaiKhoanThu;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThuChiActivity> getDataFromDb1(){
        arrThuChi = new ArrayList<>();
        Cursor cursor = db.getData("SELECT *  FROM " + MyDatabaseHelper.TBL_NAME_THUCHI );
        arrThuChi.clear();
        while (cursor.moveToNext()){
            arrThuChi.add(new ThuChiActivity(cursor.getInt(0), LocalDate.parse(cursor.getString(5)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
        }
        cursor.close();
        return arrThuChi;
    }


    private void addEvents() {
        lvTaiKhoanThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TaiKhoanChiTietActivity.class);
                adapter = new TaiKhoanAdapter(getActivity(), R.layout.item_tai_khoan_layout,InfoTaiKhoanThu);
                TaiKhoanInfo taiKhoanActivity= (TaiKhoanInfo) adapter.getItem(i);
                intent.putExtra("Tai Khoan",taiKhoanActivity);
                startActivity(intent);
            }
        });
    }
}
