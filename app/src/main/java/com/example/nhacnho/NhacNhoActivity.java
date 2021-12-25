package com.example.nhacnho;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nhacnho.model.NhacNho;
import com.example.nhacnho.adapter.NhacNhoAdapter;
import com.example.nhacnho.fragment.FragmentNhacNhoMainDataNotNull;
import com.example.nhacnho.fragment.FragmentNhacNhoMainDataNull;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhacNhoActivity extends Fragment {
//    ListView lvNhacNho;
    public static ArrayList<NhacNho> nhacNhos;
    LinearLayout layoutContainer;
    NhacNhoAdapter adapter;
    public  static MyDatabaseHelper db;
    FloatingActionButton btnThemMoi;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_nhacnho_main,container,false);
        prepareDb();

        btnThemMoi=root.findViewById(R.id.btnNhacNhoTao);


        addEvents();
        return root;

    }

    private void setView() {
        FragmentManager manager= getActivity().getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        Fragment fragment= null;
        if (nhacNhos.size()==0){
            fragment=new FragmentNhacNhoMainDataNull();
        }else {
            fragment=new FragmentNhacNhoMainDataNotNull();
        }
        transaction.replace(R.id.layoutContainer, fragment);

        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        getDataFromDb();
        setView();
        super.onResume();
    }


    private void prepareDb() {
        db = new MyDatabaseHelper(getContext());
//        db.createSomeNhacNhoData();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getDataFromDb() {
        nhacNhos = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_NHAC_NHO);
        nhacNhos.clear();
        while(cursor.moveToNext()){
//            activity.add(new ThuChiActivity(cursor.getInt(0), cursor.getString(1)));
            nhacNhos.add(new NhacNho(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),LocalDate.parse( cursor.getString(4)),Time.valueOf(cursor.getString(5))  ));
        }
        cursor.close();


    }


    private void addEvents() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NhacNhoThemActivity.class);
                startActivity(intent);
            }
        });

    }
}