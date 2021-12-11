package com.example.muctieutietkiem.packages;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import com.example.muctieutietkiem.packages.adapter.GoalAdapter;
import com.example.muctieutietkiem.packages.model.Goal;
import com.example.nhacnho.NhacNho;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.time.format.DateTimeFormatter;

public class MucTieuChiTiet extends AppCompatActivity implements DialogThemTien.OnInputListener {
    public static final String TAG ="MucTieuChiTiet";
    Button btnHoanThanh, btnThemTien;
    TextView txtTenMucTieu, txtNgayKetThuc, txtPercent,txtSoTienDatDuoc,txtTest;
    ImageView imvGoal;
    ProgressBar progressBar;


    Goal selectedGoal=null;
    @RequiresApi(api = Build.VERSION_CODES.O)
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
        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogComplete = new Dialog(MucTieuChiTiet.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                dialogComplete.setContentView(R.layout.dialog_error);
                TextView txtTitlePause=dialogComplete.findViewById(R.id.txtTitle),
                        txtMessagePause=dialogComplete.findViewById(R.id.txtMessage);
                Button btnYesComplete=dialogComplete.findViewById(R.id.btnYes),
                        btnNoComplete=dialogComplete.findViewById(R.id.btnNo);
                txtTitlePause.setText("Xác nhận");
                txtMessagePause.setText("Bạn có muốn đặt mục tiêu là đã hoàn thành?"+"\n"+"Nó sẽ được chuyển tới trang Hoàn thành và bạn không thể hoàn tác lại. ");
                btnYesComplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hoat_dong_fragment.db.execSql("DELETE FROM "+ MyDatabaseHelper.TBL_NAME_MUC_TIEU+" WHERE "+MyDatabaseHelper.COL_MUCTIEU_ID + "=" +selectedGoal.getGoalID());
                        hoat_dong_fragment.db.insertMucTieuCompletedData(selectedGoal.getGoalThumb(),selectedGoal.getGoalName(),selectedGoal.getGoalTime(),selectedGoal.getGoalColor(),selectedGoal.getGoalSaved(),selectedGoal.getGoalTarget(),selectedGoal.getGoalNote());
//                        hoat_dong_fragment.db.updateData(selectedGoal.getGoalID(),goalThumb,ten,goalTime,color,goalSaved,goalTarget,luuY);

                        finish();
                    }
                });
                btnNoComplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogComplete.dismiss();
                    }
                });
                dialogComplete.show();
            }
        });
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

        if(tien>selectedGoal.getGoalTarget())
        {
            AlertDialog.Builder builder= new AlertDialog.Builder(MucTieuChiTiet.this);
            builder.setTitle("Lỗi!");
            builder.setMessage("Bạn kiểm tra lại Số tiền thêm vào nhé. Có vẻ như nó đang lớn hơn số tiền mục tiêu í .-.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
        else {
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
}
