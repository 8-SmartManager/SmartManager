package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CaiDatTroGiup extends AppCompatActivity {

    TextView txtNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat_tro_giup);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Cài đặt Trợ Giúp");

        linkViews();
        addEvents();

    }

    private void addEvents() {
        txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatTroGiup.this, CaiDatTroGiupNew.class);
                startActivity(intent);

            }
        });
    }

    private void linkViews() {
        txtNew = findViewById(R.id.txtNew);
    }


}