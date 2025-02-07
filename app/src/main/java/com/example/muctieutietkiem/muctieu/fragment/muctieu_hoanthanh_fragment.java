package com.example.muctieutietkiem.muctieu.fragment;

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

import com.example.muctieutietkiem.muctieu.MucTieuChiTiet_HoanThanh;
import com.example.muctieutietkiem.muctieu.adapter.GoalAdapter;
import com.example.muctieutietkiem.muctieu.model.Goal;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class muctieu_hoanthanh_fragment extends Fragment {
    public static MyDatabaseHelper db;
    ListView lvGoal;
    ArrayList<Goal> goals;
    GoalAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muctieu_hoanthanh, container, false);
        lvGoal=view.findViewById(R.id.lvGoal);

        lvGoal=view.findViewById(R.id.lvGoal);

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
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Goal> getDataFromDb() {
        goals = new ArrayList<>();
        Cursor cursor = muctieu_hoanthanh_fragment.db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_MUC_TIEU_COMPLETED);
        goals.clear();
        while(cursor.moveToNext()){
            goals.add(new Goal(cursor.getInt(0), cursor.getInt(5), cursor.getString(1), LocalDate.parse( cursor.getString(4)), cursor.getInt(6) , cursor.getDouble(3), cursor.getDouble(2),cursor.getString(7) ));
        }
        cursor.close();
        return goals;
    }

    private void addEvents() {

        lvGoal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(getActivity(), MucTieuChiTiet_HoanThanh.class);
                adapter= new GoalAdapter(getActivity(),R.layout.fragment_chitiet_muctieu_hoanthanh,goals);
                Goal goal= (Goal) adapter.getItem(i);
                intent.putExtra("Muc tieu",goal);
                startActivity(intent);
            }
        });
    }
}
