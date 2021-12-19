package com.example.muctieutietkiem.muctieu;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartmanagertwo.R;

public class TaoMucTieu extends AppCompatActivity {
    EditText edtTenMucTieu;
    Button btnTaoMucTieu;
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_muctieu);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Tạo mục tiêu");
        LinkView();

        addEvents();
    }

    private void addEvents() {


        btnTaoMucTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTenMucTieu.getText().length()==0)
                {
                    ShowDialog();
                }
                else {
                    intent= new Intent(TaoMucTieu.this,TaoMucTieuChiTiet.class);
                    intent.putExtra("tlName",edtTenMucTieu.getText().toString());

                    startActivity(intent);
                    finish();
                }
            }
        });

    }

//    private void loadFragment() {
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        Fragment fragment = null;
//        fragment = new HopChonTheLoaiTietKiem();
//        transaction.replace(R.id.LayoutContainerTietKiem, fragment,"fragMucTieu");
//
//        transaction.commit();
//
//
//
//    }

    private void LinkView() {
        edtTenMucTieu= findViewById(R.id.edtTenMucTieu);
        btnTaoMucTieu=findViewById(R.id.btnTaoMucTieu);


    }

    private void ShowDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_nhapmuctieu);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

}
