package com.example.muctieutietkiem.packages;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.muctieutietkiem.packages.adapter.GoalAdapter;
import com.example.muctieutietkiem.packages.model.Goal;
import com.example.nhacnho.NhacNho;
import com.example.smartmanagertwo.R;

public class MucTieuChiTiet extends AppCompatActivity {
    Button btnHoanThanh, btnThemTien;
    TextView txtTenMucTieu, txtNgayKetThuc, txtPercent,txtSoTienDatDuoc;
    ImageView imvGoal;
    ProgressBar progressBar;
    Goal goal=null;
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
    }

    private void getData() {
        Intent intent= getIntent();
        goal= (Goal) intent.getSerializableExtra("Muc tieu");
        txtTenMucTieu.setText(goal.getGoalName());
        imvGoal.setImageResource(goal.getGoalThumb());
        txtNgayKetThuc.setText(goal.getGoalTime());
        double percent = (goal.getGoalSaved()/goal.getGoalTarget())*100;
        txtPercent.setText(String.format("%,.0f",percent)+"%");
//        txtPercent.setText(String.valueOf(percent));
        txtSoTienDatDuoc.setText(String.format("%,.0f",goal.getGoalSaved()));
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

                startActivity(intent);
                break;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
