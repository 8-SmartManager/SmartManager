package com.example.smartmanagertwo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SuaHoatDong extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_hoat_dong);

        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnBack = findViewById(R.id.btnBack1);
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
