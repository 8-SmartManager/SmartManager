package com.example.taikhoan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.thuchi.fragment.ThuChiHopChonTaiKhoanChi;
import com.example.thuchi.fragment.ThuChiHopChonTheLoaiChi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaiKhoanSuaChiTiet extends AppCompatActivity {
    Button btnSave;
    TextView txtNewActivityDate, txtNewActivityAccount, txtNewActivityName, txtNewActivityAmount;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thu_chi_them_moi_chi);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Thêm hoạt động");

        linkViews();
        getData();
        addEvents();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int[] sourceCoordinates = new int[2];
            v.getLocationOnScreen(sourceCoordinates);
            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }

        }
        return super.dispatchTouchEvent(ev);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            activity.getWindow().getDecorView();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tai_khoan_them_moi_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        intent= getIntent();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                String activityDate = txtNewActivityDate.getText().toString();
                String activityAccount = txtNewActivityAccount.getText().toString();
                String activityAmount = txtNewActivityAmount.getText().toString();
                String activityName = txtNewActivityName.getText().toString();
                String activityType = "Chi";
                if(activityAccount.equals("") || activityName.equals("") || activityAmount.equals("") || activityDate.equals("")){
                    AlertDialog.Builder builder= new AlertDialog.Builder(TaiKhoanSuaChiTiet.this);
                    builder.setTitle("Lỗi!");
                    builder.setMessage("Vui lòng nhập đầy đủ thông tin");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();

                }else{
                    TaiKhoanChiTietActivity.db.execSql("INSERT INTO " + MyDatabaseHelper.TBL_NAME_THUCHI + " VALUES(null, '" + activityType+ "', '" + activityName + "', '" + activityAccount + "', '" + activityAmount + "', '" + activityDate + "')");
                    onBackPressed();
                    Toast.makeText(TaiKhoanSuaChiTiet.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                }

            }
        });


        txtNewActivityDate.setOnClickListener(myClick);
        txtNewActivityAccount.setOnClickListener(myClick);
        txtNewActivityName.setOnClickListener(myClick);
        txtNewActivityAmount.setOnClickListener(myClick);
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
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    fragment= new ThuChiHopChonTaiKhoanChi();

                }
                if(view.getId()==R.id.txtNewActivityName ){
                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    fragment= new ThuChiHopChonTheLoaiChi();



                }

                if(view.getId()==R.id.txtNewActivityAmount){

                    txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                    txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.thu_cap));
                    txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));



                }

                transaction.replace(R.id.layoutContainerThuChiThemMoi, fragment);
                transaction.commit();}

            if (view.getId() == R.id.txtNewActivityDate) {
                txtNewActivityAccount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                txtNewActivityName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                txtNewActivityAmount.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.mau_xam));
                txtNewActivityDate.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext().getApplicationContext(),R.color.thu_cap));

                Calendar calendarDate = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR, i);
                        calendarDate.set(Calendar.MONTH, i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH, i2);
                        txtNewActivityDate.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(TaiKhoanSuaChiTiet.this, callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        }
    };



    private void linkViews() {
        txtNewActivityDate = findViewById(R.id.txtNewActivityDate);
        txtNewActivityAccount = findViewById(R.id.txtNewActivityAccount);
        txtNewActivityName = findViewById(R.id.txtNewActivityName);
        txtNewActivityAmount = findViewById(R.id.txtNewActivityAmount);

        btnSave = findViewById(R.id.btnSave);
    }
}