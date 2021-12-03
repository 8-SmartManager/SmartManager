package com.example.smartmanagertwo;

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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.ThuChiActivity;
import com.example.thongke.ThongKe;
import com.example.thongke.ThongKeChiTiet;
import com.example.thongke.ThongKeChiTietAdapter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongKeChiTietActivity extends AppCompatActivity {

    //ImageButton btnBack;

    ListView lvThongKeChiTiet;
    ArrayList<ThuChiActivity> InfoTKChiTiet;
    ThongKeChiTietAdapter chiTietAdapter;
    ThongKe selectedThongKe;
    public static MyDatabaseHelper db;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_chi_chi_tiet);

        getData();
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));

        if (selectedThongKe.getInfoCategory().equals("Ăn uống")){
            getSupportActionBar().setTitle("Ăn uống");
        }else if(selectedThongKe.getInfoCategory().equals("Giải trí")){
            getSupportActionBar().setTitle("Giải trí");
        }else if(selectedThongKe.getInfoCategory().equals("Mua sắm")){
            getSupportActionBar().setTitle("Mua sắm");
        }

        prepareDb();
        linkViews();

        //initData();

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
            case R.id.mnLich:
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
        //btnBack = findViewById(R.id.btnThongKeChiTietBack);
        lvThongKeChiTiet = findViewById(R.id.lvThongKeChiTiet);

    }

    private void prepareDb() {
        db = new MyDatabaseHelper(this);


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThuChiActivity> getDataFromDb(){
        InfoTKChiTiet = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THUCHI + " WHERE "+MyDatabaseHelper.COL_THUCHI_TYPE+"='"+selectedThongKe.getInfoThuOrChi()+"' AND " + MyDatabaseHelper.COL_THUCHI_NAME + " = '" + selectedThongKe.getInfoCategory() + "'");
        InfoTKChiTiet.clear();
        while (cursor.moveToNext()){
            InfoTKChiTiet.add(new ThuChiActivity(cursor.getInt(0), LocalDate.parse(cursor.getString(5)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
        }
        cursor.close();
        return InfoTKChiTiet;
    }


    //    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void initData() {
//        InfoTKChiTiet = new ArrayList<ThongKeChiChiTiet>();
//        InfoTKChiTiet.add(new ThongKeChiChiTiet("Tiền mặt", "Giải trí", 200000, LocalDate.of(2021, 11, 27)));
//        InfoTKChiTiet.add(new ThongKeChiChiTiet("Tài khoản ngân hàng", "Mua sắm", 500000, LocalDate.of(2021, 11, 27)));
//        InfoTKChiTiet.add(new ThongKeChiChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
//        InfoTKChiTiet.add(new ThongKeChiChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
//        InfoTKChiTiet.add(new ThongKeChiChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
//        InfoTKChiTiet.add(new ThongKeChiChiTiet("Thẻ tín dụng", "Ăn uống", 2000000, LocalDate.of(2021, 11, 27)));
//    }
    private void getData() {
        Intent intent = getIntent();
        selectedThongKe = (ThongKe) intent.getSerializableExtra("Thong Ke");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        chiTietAdapter = new ThongKeChiTietAdapter(ThongKeChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, getDataFromDb());
        lvThongKeChiTiet.setAdapter(chiTietAdapter);
    }

    private void addEvents() {
        //Nút back
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
        //Tạo sự kiện listview
        lvThongKeChiTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ThongKeChiTietActivity.this, ThongKeChinhSua.class);
                chiTietAdapter = new ThongKeChiTietAdapter(ThongKeChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, InfoTKChiTiet);
                ThuChiActivity thongKeChiTiet = (ThuChiActivity) chiTietAdapter.getItem(i);
                intent.putExtra("ThongKeChiTiet", thongKeChiTiet);
                startActivity(intent);
            }
        });
    }
}
