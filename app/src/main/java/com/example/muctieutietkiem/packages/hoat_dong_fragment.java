package com.example.muctieutietkiem.packages;

import android.content.Context;
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
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.muctieutietkiem.packages.adapter.GoalAdapter;
import com.example.muctieutietkiem.packages.model.Goal;
import com.example.nhacnho.NhacNho;
import com.example.nhacnho.NhacNhoAdapter;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.NhacNhoActivity;
import com.example.smartmanagertwo.NhacNhoChiTietActivity;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.kehoach_muctieu_tietkiem;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class hoat_dong_fragment extends Fragment{

    MyDatabaseHelper db;

    ListView lvGoal;
    ArrayList<Goal> goals;
    GoalAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hoat_dong_fragment, container, false);

        lvGoal=view.findViewById(R.id.lvGoal);


        goals=new ArrayList<>();
        lvGoal=view.findViewById(R.id.lvGoal);
        goals.add(new Goal(1,R.drawable.ic_car,"Mua xe",LocalDate.of(2021,11,20),-11873872,1500000,5000000,"Quan trọng"));
        goals.add(new Goal(2,R.drawable.ic_nha,"Mua nhà",LocalDate.of(2021,11,20),-11873872,0,10000000,"Quan trọng"));
        adapter = new GoalAdapter(getContext(),R.layout.custom_muctieu_tietkiem,goals);
        lvGoal.setAdapter(adapter);
        addEvents();
        return view;
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        db= new MyDatabaseHelper(context);
//        db.createSomeData();
//    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private List<Goal> getDataFromDb() {
//        goals = new ArrayList<>();
//        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_MUC_TIEU);
//        goals.clear();
//        while(cursor.moveToNext()){
////            activity.add(new ThuChiActivity(cursor.getInt(0), cursor.getString(1)));
//            goals.add(new Goal(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), LocalDate.parse( cursor.getString(3)), cursor.getInt(4) , cursor.getDouble(5), cursor.getDouble(6),cursor.getString(7) ));
//        }
//        cursor.close();
//        return goals;
//    }

    private void addEvents() {

        lvGoal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    Intent intent = new Intent(getActivity(), MucTieuChiTiet.class);
                    adapter= new GoalAdapter(getActivity(),R.layout.chitiet_muctieu,goals);
                    Goal goal= (Goal) adapter.getItem(i);
                    intent.putExtra("Muc tieu",goal);
                    startActivity(intent);
                }

        });


   }
}
