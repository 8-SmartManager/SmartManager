package com.example.KeHoachMuaSam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartmanagertwo.R;

public class DanhSachMuaSam2 extends AppCompatActivity {

    TextView txtTaiKhoan, txtTheLoai, txtTitle;
    EditText edtSoTien;
    Button btnThem;
    FragmentManager manager;
    FragmentTransaction transaction;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mua_sam2);

        linkViews();
        addEvent();

    }


    private void addEvent() {
        txtTaiKhoan.setOnClickListener(myClick);
        txtTheLoai.setOnClickListener(myClick);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DanhSachMuaSam2.this, DanhSachMuaSam3.class);
                startActivity(intent);
            }
        });
    }

    private void linkViews() {
        txtTaiKhoan=findViewById(R.id.txtTaiKhoan);
        txtTheLoai=findViewById(R.id.txtTheLoai);
        edtSoTien=findViewById(R.id.edtSoTien);
        txtTitle=findViewById(R.id.txtTitle);

        btnThem=findViewById(R.id.btnThem);

    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.txtTaiKhoan||view.getId()==R.id.txtTheLoai )
            {
                FragmentManager manager= getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                Fragment fragment= null;
                if(view.getId()==R.id.txtTaiKhoan){
                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    edtSoTien.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment= new HopChonTaiKhoan();

                }
                if(view.getId()==R.id.txtTheLoai ){
                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    edtSoTien.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment= new HopChonTheLoai();
                }

                transaction.replace(R.id.layoutContainer, fragment);
                transaction.commit();
            }


        }
    };
}