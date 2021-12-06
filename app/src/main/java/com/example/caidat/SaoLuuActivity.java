package com.example.caidat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.ThongKeChiTietActivity;

import java.io.InputStream;

public class SaoLuuActivity extends AppCompatActivity {

    LinearLayout chooseGGDrive, chooseThietBi, chooseTatCaKhoiTao, chooseChuKyKhoiTao;
    TextView txtChoice;

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

        txtChoice = findViewById(R.id.txtChoice);
    }

    private void addEvents() {
        chooseGGDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaoLuuActivity.this, SaoLuuGGDrive.class);
                startActivity(intent);
            }
        });
        txtChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.txtChoice){
                    if (txtChoice.getText().equals("Tắt")){
                        txtChoice.setText("Bật");
                    }else {
                        txtChoice.setText("Tắt");
                    }
                }
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
                Dialog dialog = new Dialog(SaoLuuActivity.this, R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_error);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle), txtMessage = dialog.findViewById(R.id.txtMessage);
                Button btnYes = dialog.findViewById(R.id.btnYes), btnNo = dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Thông báo!");
                txtMessage.setText("Bạn có chắc chắn muốn thiết lập lại?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        chooseChuKyKhoiTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(SaoLuuActivity.this, R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_error);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle), txtMessage = dialog.findViewById(R.id.txtMessage);
                Button btnYes = dialog.findViewById(R.id.btnYes), btnNo = dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Thông báo!");
                txtMessage.setText("[Chu kỳ] sẽ thiết lập lại. Thể loại, Tài khoản, Cài đặt không reset. Bạn có muốn tiếp tục không?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
