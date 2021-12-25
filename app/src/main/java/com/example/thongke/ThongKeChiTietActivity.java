package com.example.thongke;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.thongke.adapter.ThongKeChiTietAdapter;
import com.example.thuchi.fragment.ThuChiChinh;
import com.example.thuchi.model.ThuChiActivity;
import com.example.thongke.model.ThongKe;
import com.example.thuchi.ThuChiChinhSua;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongKeChiTietActivity extends AppCompatActivity {

    ListView lvThongKeChiTiet;
    ArrayList<ThuChiActivity> InfoTKChiTiet;
    ThongKeChiTietAdapter chiTietAdapter;
    ThongKe selectedThongKe;
    TextView txtTimePeriod, txtTotalAmount;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_chi_tiet);

        getData();
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle(selectedThongKe.getInfoCategory());
//        if (selectedThongKe.getInfoCategory().equals("Giải trí")){
//            getSupportActionBar().setTitle("Giải trí");
//        }else if(selectedThongKe.getInfoCategory().equals("Ăn uống")){
//            getSupportActionBar().setTitle("Ăn uống");
//        }else if(selectedThongKe.getInfoCategory().equals("Sở thích")){
//            getSupportActionBar().setTitle("Sở thích");
//        }else if(selectedThongKe.getInfoCategory().equals("Giáo dục")){
//            getSupportActionBar().setTitle("Giáo dục");
//        }else if(selectedThongKe.getInfoCategory().equals("Sức khỏe")){
//            getSupportActionBar().setTitle("Sức khỏe");
//        }else if(selectedThongKe.getInfoCategory().equals("Sinh hoạt")){
//            getSupportActionBar().setTitle("Sinh hoạt");
//        }else if(selectedThongKe.getInfoCategory().equals("Áo quần")){
//            getSupportActionBar().setTitle("Áo quần");
//        }else if(selectedThongKe.getInfoCategory().equals("Làm đẹp")){
//            getSupportActionBar().setTitle("Làm đẹp");
//        }else if(selectedThongKe.getInfoCategory().equals("Khác")) {
//            getSupportActionBar().setTitle("Khác");
//        }else if (selectedThongKe.getInfoCategory().equals("Trả thêm giờ")){
//            getSupportActionBar().setTitle("Trả thêm giờ");
//        }else if (selectedThongKe.getInfoCategory().equals("Tiền lương")){
//            getSupportActionBar().setTitle("Tiền lương");
//        }else if (selectedThongKe.getInfoCategory().equals("Tiền thưởng")){
//            getSupportActionBar().setTitle("Tiền thưởng");
//        }else if (selectedThongKe.getInfoCategory().equals("Tiền trợ cấp")){
//            getSupportActionBar().setTitle("Tiền trợ cấp");
//        }

        linkViews();
        loadData();
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
        getMenuInflater().inflate(R.menu.thong_ke_chi_tiet_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnChooseDate:
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongKeChiTietActivity.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        loadData();
        super.onResume();
    }

    private void linkViews() {
        lvThongKeChiTiet = findViewById(R.id.lvThongKeChiTiet);
        txtTimePeriod=findViewById(R.id.txtTimePeriod);
        txtTotalAmount=findViewById(R.id.txtTotalAmount);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThuChiActivity> getDataFromDb(){
        InfoTKChiTiet = new ArrayList<>();
        Cursor cursor = ThuChiChinh.db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THUCHI + " WHERE "+MyDatabaseHelper.COL_THUCHI_TYPE+"='"+selectedThongKe.getInfoThuOrChi()+"' AND " + MyDatabaseHelper.COL_THUCHI_NAME + " = '" + selectedThongKe.getInfoCategory() + "'");
        InfoTKChiTiet.clear();
        while (cursor.moveToNext()){
            InfoTKChiTiet.add(new ThuChiActivity(cursor.getInt(0), LocalDate.parse(cursor.getString(5)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
        }
        cursor.close();
        return InfoTKChiTiet;
    }

    private void getData() {
        Intent intent = getIntent();
        selectedThongKe = (ThongKe) intent.getSerializableExtra("Thong Ke");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        chiTietAdapter = new ThongKeChiTietAdapter(ThongKeChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, getDataFromDb());
        lvThongKeChiTiet.setAdapter(chiTietAdapter);
        txtTimePeriod.setText(ThongKeActivity.time);
        double total=0;
        for (ThuChiActivity item:InfoTKChiTiet
             ) {
            total+=item.getActivityAmount();
        }
        txtTotalAmount.setText(String.format("%,.0f",total)+" đồng");
    }

    private void addEvents() {
        //Tạo sự kiện listview
        lvThongKeChiTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ThongKeChiTietActivity.this, ThuChiChinhSua.class);
                chiTietAdapter = new ThongKeChiTietAdapter(ThongKeChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, InfoTKChiTiet);
                ThuChiActivity thongKeChiTiet = (ThuChiActivity) chiTietAdapter.getItem(i);
                intent.putExtra("ThongKeChiTiet", thongKeChiTiet);
                startActivity(intent);
            }
        });
    }
}
