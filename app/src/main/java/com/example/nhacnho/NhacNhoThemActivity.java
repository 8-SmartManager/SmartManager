package com.example.nhacnho;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nhacnho.dialogFragment.HopChonNhacNhoChuKy;
import com.example.nhacnho.dialogFragment.HopChonNhacNhoThemTheLoai;
import com.example.nhacnho.fragment.NhacNhoActivity;
import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class NhacNhoThemActivity extends AppCompatActivity {
    TextView txtTheLoai, txtChuKy, txtNgayBatDau, txtGioNhac;
    EditText edtTen;
    Button btnTao;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacnho_them_moi);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Tạo nhắc nhở");
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;}
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
         intent= getIntent();
    }
    private void addEvents() {
        txtTheLoai.setOnClickListener(myClick);
        edtTen.setOnClickListener(myClick);
        txtChuKy.setOnClickListener(myClick);
        txtNgayBatDau.setOnClickListener(myClick);
        txtGioNhac.setOnClickListener(myClick);
        btnTao.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String theLoai=txtTheLoai.getText().toString(),
                        ten=edtTen.getText().toString(),
                        chuKy=txtChuKy.getText().toString(),
                        ngayBatDau=txtNgayBatDau.getText().toString(),
                        gioNhac=txtGioNhac.getText().toString();

                if(theLoai.equals("Chọn thể loại")||ten.equals("")||ten.equals("Nhập tên")||chuKy.equals("Chọn chu kỳ")||ngayBatDau.equals("Chọn ngày bắt đầu")||gioNhac.equals("Chọn giờ nhắc")){
                    Dialog dialog = new Dialog(NhacNhoThemActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                    dialog.setContentView(R.layout.dialog_error);
                    TextView txtTitle=dialog.findViewById(R.id.txtTitle),
                            txtMessage=dialog.findViewById(R.id.txtMessage);
                    Button btnYes=dialog.findViewById(R.id.btnYes);
                    txtTitle.setText("Lỗi");
                    txtMessage.setText("Vui lòng nhập đầy đủ thông tin!");
                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }else {
                    LocalDate now=LocalDate.now();
                    LocalDate date=LocalDate.parse(ngayBatDau);
                    Calendar calendar1 = Calendar.getInstance();
                    SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
                    String currentDate = formatter1.format(calendar1.getTime());
                    if (date.compareTo(now) < 0) {
                        Dialog dialog = new Dialog(NhacNhoThemActivity.this, R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                        dialog.setContentView(R.layout.dialog_error);
                        TextView txtTitle = dialog.findViewById(R.id.txtTitle),
                                txtMessage = dialog.findViewById(R.id.txtMessage);
                        Button btnYes = dialog.findViewById(R.id.btnYes);

                        txtTitle.setText("Lỗi");
                        txtMessage.setText("Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại!");
                        btnYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();

                    } else if (date.compareTo(now) == 0 && currentDate.compareTo(gioNhac) > 0) {
                        Dialog dialog = new Dialog(NhacNhoThemActivity.this, R.style.Theme_MaterialComponents_Light_Dialog_FixedSize);
                        dialog.setContentView(R.layout.dialog_error);
                        TextView txtTitle = dialog.findViewById(R.id.txtTitle),
                                txtMessage = dialog.findViewById(R.id.txtMessage);
                        Button btnYes = dialog.findViewById(R.id.btnYes);

                        txtTitle.setText("Lỗi");
                        txtMessage.setText("Thời gian nhắc phải lớn hơn thời gian hiện tại!");
                        btnYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    } else {
                        NhacNhoActivity.db.execSql("INSERT INTO " + MyDatabaseHelper.TBL_NAME_NHAC_NHO + " VALUES(null, '" + theLoai + "', '" + ten + "', '" + chuKy + "', '" + ngayBatDau + "', '" + gioNhac + ":00')");
                        finish();
                    }
                }
            }
        });
    }
    private void linkViews() {
        txtTheLoai=findViewById(R.id.txtNhacNhoThemTheLoai);
        edtTen=findViewById(R.id.edtNhacNhoThemTen);
        txtChuKy=findViewById(R.id.txtNhacNhoThemChuKy);
        txtNgayBatDau= findViewById(R.id.txtNhacNhoThemNgayBatDau);
        txtGioNhac=findViewById(R.id.txtNhacNhoThemGioNhac);
        btnTao= findViewById(R.id.btnNhacNhoThemMoiTao);
    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.txtNhacNhoThemTheLoai||view.getId()==R.id.txtNhacNhoThemChuKy ||view.getId()==R.id.edtNhacNhoThemTen)
            {
            FragmentManager manager= getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            Fragment fragment= null;
            if(view.getId()==R.id.txtNhacNhoThemTheLoai){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoThemTheLoai();
            }
            if(view.getId()==R.id.txtNhacNhoThemChuKy){

                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoChuKy();
            }
                if(view.getId()==R.id.edtNhacNhoThemTen ){
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment= new Fragment();
                }
            transaction.replace(R.id.layoutContainerThemNhacNho, fragment);
            transaction.commit();}
            if(view.getId()==R.id.txtNhacNhoThemNgayBatDau){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                        txtNgayBatDau.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(NhacNhoThemActivity.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
            if(view.getId()==R.id.txtNhacNhoThemGioNhac){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                Calendar calendarTime= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendarTime.set(0,0,0,i,i1);
                        txtGioNhac.setText(simpleDateFormat.format(calendarTime.getTime()));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(NhacNhoThemActivity.this,callback,
                        calendarTime.get(Calendar.HOUR_OF_DAY),
                calendarTime.get(Calendar.MINUTE),true);
                timePickerDialog.show();
            }
        }
    };
}
