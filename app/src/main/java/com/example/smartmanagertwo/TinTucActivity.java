package com.example.smartmanagertwo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tintuc.TinTucAdapter;
import com.example.tintuc.TinTucData;

public class TinTucActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintuc);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TinTucData[] myNewsData = new TinTucData[]{
                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1),
                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2),
                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1),
                new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2),

        };

        TinTucAdapter myNewsAdapter = new TinTucAdapter(myNewsData, TinTucActivity.this);
        recyclerView.setAdapter(myNewsAdapter);

    }
}
