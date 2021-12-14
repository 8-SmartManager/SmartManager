package com.example.smartmanagertwo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tintuc.KetNoiTinTucChiTiet;
import com.example.tintuc.TinTucAdapter;
import com.example.tintuc.TinTucData;

import java.util.ArrayList;

public class TinTucActivity extends Fragment {
    ListView lvTinTuc;
    TinTucAdapter adapter;
    ArrayList <TinTucData> news;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_tintuc,container,false);

        lvTinTuc=root.findViewById(R.id.lvTinTuc);
        iniData();
        loadData();
        addEvent();
        return root;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addEvent() {
        lvTinTuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), KetNoiTinTucChiTiet.class);
                startActivity(intent);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter=new TinTucAdapter(getContext(),R.layout.item_tintuc,iniData());
        lvTinTuc.setAdapter(adapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<TinTucData> iniData() {
        news= new ArrayList<>();
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1));
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2));
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1));
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2));

        return news;
    }
}
