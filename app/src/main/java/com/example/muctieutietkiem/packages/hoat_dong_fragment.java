package com.example.muctieutietkiem.packages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.muctieutietkiem.packages.adapter.GoalAdapter;
import com.example.muctieutietkiem.packages.model.Goal;
import com.example.nhacnho.NhacNho;
import com.example.nhacnho.NhacNhoAdapter;
import com.example.smartmanagertwo.NhacNhoActivity;
import com.example.smartmanagertwo.NhacNhoChiTietActivity;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class hoat_dong_fragment extends Fragment {
    ListView lvGoal;
    ArrayList<Goal> goals;
    GoalAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hoat_dong_fragment, container, false);
        goals=new ArrayList<>();
        lvGoal=view.findViewById(R.id.lvGoal);
        goals.add(new Goal(R.drawable.ic_car,"Mua xe","22/11/2022",R.drawable.ic_group_61__1_,"5.000.000đ"));
        goals.add(new Goal(R.drawable.ic_nha,"Mua nhà","27/12/2024",R.drawable.ic_group_61__1_,"0đ"));
        adapter = new GoalAdapter(getContext(),R.layout.custom_muctieu_tietkiem,goals);
        lvGoal.setAdapter(adapter);
        addEvents();
        return view;
    }

    private void addEvents() {

        lvGoal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(), chitiet_muctieu_fragement.class);
                adapter=new GoalAdapter(getContext(),R.layout.custom_muctieu_tietkiem,goals);
                Goal g = (Goal) adapter.getItem(i);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction;
                transaction=manager.beginTransaction();
                chitiet_muctieu_fragement fragment = new chitiet_muctieu_fragement();
                transaction.add(R.id.layoutContainer,fragment);
                transaction.commit();
            }
        });


   }
}
