package com.example.tintuc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class TinTucActivity extends AppCompatActivity {
    ListView lvTinTuc;
    TinTucAdapter adapter;
    ArrayList <TinTucData> news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintuc);

        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Tin Tức");

        linkViews();
        iniData();
        loadData();
        addEvent();
    }

    private void addEvent() {
        lvTinTuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(TinTucActivity.this,KetNoiTinTucChiTiet.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        adapter=new TinTucAdapter(getBaseContext(),R.layout.item_tintuc,iniData());
        lvTinTuc.setAdapter(adapter);
    }



    private void linkViews() {
        lvTinTuc=findViewById(R.id.lvTinTuc);
    }
    private ArrayList<TinTucData> iniData() {
        news= new ArrayList<>();
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1));
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2));
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh1));
        news.add(new TinTucData("Personal Finance Tips for 2020: “The Simple Path to Wealth","Find your road map to financial independence and a rich life through simple awareness.",R.drawable.anh2));

        return news;
    }

}
