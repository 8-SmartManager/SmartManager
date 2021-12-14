package com.example.smartmanagertwo;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tintuc.TinTucAdapter;
import com.example.tintuc.TinTucData;

public class TinTucActivity extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_tintuc,container,false);

//        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        TinTucData[] myNewsData = new TinTucData[]{
//                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1),
//                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2),
//                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1),
//                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2),
//
//        };
//
//        TinTucAdapter myNewsAdapter = new TinTucAdapter(myNewsData, getContext());
//        recyclerView.setAdapter(myNewsAdapter);
return root;
    }
}
