package com.example.muctieutietkiem.packages;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import com.example.muctieutietkiem.packages.model.Goal;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.time.format.DateTimeFormatter;

public class MucTieuChiTiet_HoanThanh extends AppCompatActivity {

    TextView txtTenMucTieu, txtNgayKetThuc, txtPercent,txtSoTienDatDuoc,txtTest;
    ImageView imvGoal;
    ProgressBar progressBar;


    Goal selectedGoal=null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_muctieu_hoanthanh);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Chi tiết mục tiêu hoàn thành");
        LinkView();

        getData();
        addEvents();


    }

    private void addEvents() {

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getData() {
        Intent intent= getIntent();
        selectedGoal= (Goal) intent.getSerializableExtra("Muc tieu");

        byte[] goalThumb = selectedGoal.getGoalThumb();
        Bitmap bitmap = BitmapFactory.decodeByteArray(goalThumb,0,goalThumb.length);
        imvGoal.setImageBitmap(bitmap);
        txtTenMucTieu.setText(selectedGoal.getGoalName());
        txtNgayKetThuc.setText(String.valueOf(selectedGoal.getGoalTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        ImageViewCompat.setImageTintList(imvGoal, ColorStateList.valueOf(selectedGoal.getGoalColor()));
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

        txtTenMucTieu=findViewById(R.id.txtTenMucTieu);
        txtNgayKetThuc=findViewById(R.id.txtNgayKetThuc);
        txtPercent=findViewById(R.id.txtPercent);
        txtSoTienDatDuoc=findViewById(R.id.txtSoTienDatDuoc);
        imvGoal=findViewById(R.id.imvGoal);
        progressBar=findViewById(R.id.progressBar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hoanthanh_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnDelete:
                Dialog dialog = new Dialog(MucTieuChiTiet_HoanThanh.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialog.setContentView(R.layout.dialog_thong_bao);
                TextView txtTitle=dialog.findViewById(R.id.txtTitle),
                        txtMessage=dialog.findViewById(R.id.txtMessage);
                Button btnYes=dialog.findViewById(R.id.btnYes),
                        btnNo=dialog.findViewById(R.id.btnNo);
                txtTitle.setText("Xác nhận");
                txtMessage.setText("Bạn có chắc chắn muốn xóa?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        muctieu_hoanthanh_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU_COMPLETED+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());

                        finish();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }


}
