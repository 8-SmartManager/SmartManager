package com.example.smartmanagertwo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.model.ThuChiActivity;
import com.example.taikhoan.TaiKhoanActivity;
import com.example.taikhoan.TaiKhoanAdapter;
import com.example.thongke.ThongKe;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoan extends Fragment {

    ListView lvTaiKhoanThu;
    TextView txtTongTK;
    ArrayList<TaiKhoanActivity> InfoTaiKhoanThu;
    TaiKhoanAdapter adapter;
    public static MyDatabaseHelper db;

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
        loadData();
        addEvents();
        return root;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.taikhoan_tuychon_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.taikhoan_tuychon_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnSua:
                Intent intents = new Intent(getActivity(), TaiKhoanChinhSua.class);
                startActivity(intents);
                Toast.makeText(getContext(), "Bạn vừa chọn Sửa tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnXoa:
                Intent myintent = new Intent(getActivity(), TaiKhoanXoa.class);
                startActivity(myintent);
                Toast.makeText(getContext(), "Bạn vừa chọn Xóa tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnThem:
                Intent intent = new Intent(getActivity(), TaiKhoanThem.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Bạn vừa chọn Thêm tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnThongKe:
                Intent intent_act = new Intent(getActivity(), ThongKe.class);
                startActivity(intent_act);
                Toast.makeText(getContext(), "Bạn chọn thống kê", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

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
        double total=0;
        for (TaiKhoanActivity item:InfoTaiKhoanThu
        ) {
            total+=item.getInfoSoTien();
        }
        txtTongTK.setText(String.format("%,.0f",total)+" đồng");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<TaiKhoanActivity> getDataFromDb(){
        InfoTaiKhoanThu = new ArrayList<>();
        Cursor cursor = db.getData("SELECT "+MyDatabaseHelper.COL_THUCHI_ACCOUNT + " , " + " SUM(" +MyDatabaseHelper.COL_THUCHI_AMOUNT + " ) " + " FROM " + MyDatabaseHelper.TBL_NAME_THUCHI + " GROUP BY " + MyDatabaseHelper.COL_THUCHI_ACCOUNT);
        InfoTaiKhoanThu.clear();
        while (cursor.moveToNext()){
            InfoTaiKhoanThu.add(new TaiKhoanActivity(cursor.getString(0),cursor.getDouble(1)));
        }
        cursor.close();
        return InfoTaiKhoanThu;
    }


    private void addEvents() {
        lvTaiKhoanThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TaiKhoanChiTietActivity.class);
                adapter = new TaiKhoanAdapter(getActivity(), R.layout.item_tai_khoan_layout,InfoTaiKhoanThu);
                TaiKhoanActivity taiKhoanActivity= (TaiKhoanActivity) adapter.getItem(i);
                intent.putExtra("Tai Khoan",taiKhoanActivity);
                startActivity(intent);
            }
        });
    }


}
