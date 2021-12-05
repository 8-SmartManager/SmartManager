package com.example.caidat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmanagertwo.R;

import java.io.InputStream;

public class SaoLuuActivity extends AppCompatActivity {

    LinearLayout chooseGGDrive, chooseThietBi, chooseTatCaKhoiTao, chooseChuKyKhoiTao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cai_dat_sao_luu);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Sao Lưu");

        linkViews();
        addEvents();
    }

    private void linkViews() {
        chooseGGDrive = findViewById(R.id.chooseGGDrive);
        chooseThietBi = findViewById(R.id.chooseThietBi);
        chooseTatCaKhoiTao = findViewById(R.id.chooseTatCaKhoiTao);
        chooseChuKyKhoiTao = findViewById(R.id.chooseChuKyKhoiTao);
    }

    private void addEvents() {
        chooseGGDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaoLuuActivity.this, SaoLuuGGDrive.class);
                startActivity(intent);
            }
        });
        chooseThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaoLuuActivity.this, SaoLuuThietBi.class);
                startActivity(intent);
            }
        });
        chooseTatCaKhoiTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        chooseChuKyKhoiTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
