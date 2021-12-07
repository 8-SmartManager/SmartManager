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

import com.example.muctieutietkiem.packages.adapter.GoalAdapter;
import com.example.muctieutietkiem.packages.model.Goal;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class hoat_dong_fragment extends Fragment{

    public static MyDatabaseHelper db;

    ListView lvGoal;
    ArrayList<Goal> goals;
    GoalAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hoat_dong_fragment, container, false);

        lvGoal=view.findViewById(R.id.lvGoal);



        lvGoal=view.findViewById(R.id.lvGoal);
//        goals.add(new Goal(1,R.drawable.ic_car,"Mua xe",LocalDate.of(2022,11,22),-11873872,1500000,5000000,"Quan trọng"));
//        goals.add(new Goal(2,R.drawable.ic_nha,"Mua nhà",LocalDate.of(2024,10,24),-11873872,0,10000000,"Quan trọng"));

        addEvents();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter = new GoalAdapter(getContext(),R.layout.item_muctieu,getDataFromDb());
        lvGoal.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db= new MyDatabaseHelper(context);
//        db.createSomeMucTieuHoatDong();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Goal> getDataFromDb() {
        goals = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_MUC_TIEU);
        goals.clear();
        while(cursor.moveToNext()){
//            activity.add(new ThuChiActivity(cursor.getInt(0), cursor.getString(1)));
            goals.add(new Goal(cursor.getInt(0), cursor.getBlob(5), cursor.getString(1), LocalDate.parse( cursor.getString(4)), cursor.getInt(6) , cursor.getDouble(3), cursor.getDouble(2),cursor.getString(7) ));

        }
        cursor.close();
        return goals;
    }

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
