package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CaiDatMatKhauMain extends AppCompatActivity {
    TextView txtChange, txtOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat_mat_khau_main);
        linkViews();
        addEvents();
    }

    private void addEvents() {
        txtOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    private void linkViews() {
        txtOff= findViewById(R.id.txtOff);
        txtChange= findViewById(R.id.txtChange);
    }
}