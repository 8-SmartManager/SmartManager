package com.example.muctieutietkiem.packages;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.muctieutietkiem.packages.adapter.GoalAdapter;
import com.example.muctieutietkiem.packages.model.Goal;
import com.example.nhacnho.NhacNho;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

public class MucTieuChiTiet extends AppCompatActivity implements DialogThemTien.OnInputListener {
    public static final String TAG ="MucTieuChiTiet";
    Button btnHoanThanh, btnThemTien;
    TextView txtTenMucTieu, txtNgayKetThuc, txtPercent,txtSoTienDatDuoc,txtTest;
    ImageView imvGoal;
    ProgressBar progressBar;


    Goal selectedGoal=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_muctieu);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Chi tiết mục tiêu");
        LinkView();

        getData();
        addEvents();


    }

    private void addEvents() {
        btnThemTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: opening dialog");

                DialogThemTien dialog = new DialogThemTien();
                dialog.show(getSupportFragmentManager(),"DialogThemTien");


            }

        });
    }

//    private void ShowDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_themtien);
//        EditText edtThemTien = dialog.findViewById(R.id.edtThemTien);
//        Button btnOk = dialog.findViewById(R.id.btnOk);
//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                double tienThem = Double.parseDouble(String.valueOf(edtThemTien.getText()));
////                Intent intent = new Intent(IntentAction.INTENT_ADD_TASK)
//                dialog.dismiss();
//
//            }
//        });
//        dialog.show();
//    }

    private void getData() {
        Intent intent= getIntent();
        selectedGoal= (Goal) intent.getSerializableExtra("Muc tieu");
        txtTenMucTieu.setText(selectedGoal.getGoalName());
        imvGoal.setImageResource(selectedGoal.getGoalThumb());
        txtNgayKetThuc.setText(String.valueOf(selectedGoal.getGoalTime()));
        double percent = (selectedGoal.getGoalSaved()/selectedGoal.getGoalTarget())*100;
        if((selectedGoal.getGoalSaved()*100)%selectedGoal.getGoalTarget()>0){
            txtPercent.setText(String.format("%,.1f",percent)+"%");
        }else {
            txtPercent.setText(String.format("%,.0f",percent)+"%");
        }

        txtSoTienDatDuoc.setText(String.format("%,.0f",selectedGoal.getGoalSaved()));
        progressBar.setProgress((int) percent);

    }

    private void LinkView() {
        btnHoanThanh=findViewById(R.id.btnHoanThanh);
        btnThemTien=findViewById(R.id.btnThemTien);
        txtTenMucTieu=findViewById(R.id.txtTenMucTieu);
        txtNgayKetThuc=findViewById(R.id.txtNgayKetThuc);
        txtPercent=findViewById(R.id.txtPercent);
        txtSoTienDatDuoc=findViewById(R.id.txtSoTienDatDuoc);
        imvGoal=findViewById(R.id.imvGoal);
        progressBar=findViewById(R.id.progressBar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hoatdong_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.editMucTieu:
                Intent intent = new Intent(MucTieuChiTiet.this,EditMucTieuHoatDong.class);


                intent.putExtra("Muc tieu",selectedGoal);
                startActivity(intent);
                finish();
                break;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendInput(String tienThem) {
        Intent intent= getIntent();
        selectedGoal= (Goal) intent.getSerializableExtra("Muc tieu");
        Log.d(TAG, "sendInput: got the input: "+ tienThem);

        double soTienThem = Double.parseDouble(tienThem);
        double tien = soTienThem+selectedGoal.getGoalSaved();


        selectedGoal.setGoalSaved(tien);
        hoat_dong_fragment.db.execSql("UPDATE "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU+" SET "+MyDatabaseHelper.COL_MUCTIEU_SOTIENDATDUOC+" = '"+tien+"'WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID+"=" +selectedGoal.getGoalID());

        txtSoTienDatDuoc.setText(String.format("%,.0f",tien));
        double percent = (selectedGoal.getGoalSaved()/selectedGoal.getGoalTarget())*100;
        if((selectedGoal.getGoalSaved()*100)%selectedGoal.getGoalTarget()>0){
            txtPercent.setText(String.format("%,.1f",percent)+"%");
        }else {
            txtPercent.setText(String.format("%,.0f",percent)+"%");
        }
        progressBar.setProgress((int) percent);
    }
}
