package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ThuChiThemMoi extends AppCompatActivity {

    Button btnMucChi, btnMucThu;
    ImageButton btnThuChiBack;
    TextView txtMucHoatDong, txtNewActivityDate, txtNewActivityAccount, txtNewActivityName, txtNewActivityAmount;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_chi_them_moi);

        linkViews();
        addEvents();

    }

    private void addEvents() {
        btnThuChiBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtNewActivityAccount.setOnClickListener(myClick);
        txtNewActivityName.setOnClickListener(myClick);
        txtNewActivityAmount.setOnClickListener(myClick);


    }

    private void linkViews() {
        btnThuChiBack = findViewById(R.id.btnThuChiBack);
        txtMucHoatDong = findViewById(R.id.txtMucHoatDong);
        txtNewActivityDate = findViewById(R.id.txtNewActivityDate);
        txtNewActivityAccount = findViewById(R.id.txtNewActivityAccount);
        txtNewActivityName = findViewById(R.id.txtNewActivityName);
        txtNewActivityAmount = findViewById(R.id.txtNewActivityAmount);

    }

    View.OnClickListener myClick = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.txtNewActivityAccount||view.getId()==R.id.txtNewActivityName||view.getId()==R.id.txtNewActivityAmount)
            {
                FragmentManager manager= getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                Fragment fragment= null;
                if(view.getId()==R.id.txtNewActivityAccount){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment= new HopChonNhacNhoThemTheLoai();

                }
                if(view.getId()==R.id.txtNewActivityName ){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment= new HopChonNhacNhoThemTheLoai();



                }

                if(view.getId()==R.id.txtNewActivityAmount){

                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));

                    fragment= new HopChonNhacNhoThemTheLoai();

                }
                transaction.replace(R.id.layoutContainerThuChiThemMoi, fragment);
                transaction.commit();}



        }
    };
}