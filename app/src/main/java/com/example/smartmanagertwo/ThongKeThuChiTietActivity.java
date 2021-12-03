//package com.example.smartmanagertwo;
//
//import android.app.Activity;
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.model.ThuChiActivity;
//import com.example.thongke.ThongKe;
//import com.example.thongke.ThongKeChiTiet;
//import com.example.thongke.ThongKeChiTietAdapter;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//public class ThongKeThuChiTietActivity extends AppCompatActivity {
//
//    ListView lvThongKeThuChiTiet;
//    ArrayList<ThuChiActivity> InfoTKThuChiTiet;
//    ThongKeChiTietAdapter thuChiTietAdapter;
//    ThongKe selectedThongKeThu;
//    public static MyDatabaseHelper db;
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_thong_ke_thu_chi_tiet);
//
//        getData();
//
//        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(drawable);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
//        if (selectedThongKeThu.getInfoCategory().equals("Tiền lương")){
//            getSupportActionBar().setTitle("Tiền lương");
//        }else if(selectedThongKeThu.getInfoCategory().equals("Tiền thưởng")){
//            getSupportActionBar().setTitle("Tiền thưởng");
//        }else if(selectedThongKeThu.getInfoCategory().equals("Tiền cấp")){
//            getSupportActionBar().setTitle("Tiền cấp");
//        }else if(selectedThongKeThu.getInfoCategory().equals("Trả thêm giờ")){
//            getSupportActionBar().setTitle("Trả thêm giờ");
//        }else if(selectedThongKeThu.getInfoCategory().equals("Khác")){
//            getSupportActionBar().setTitle("Khác");
//        }
//
//        prepareDb();
//        linkViews();
//
//        loadData();
//        addEvents();
//
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        View v = getCurrentFocus();
//
//        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
//                v instanceof EditText &&
//                !v.getClass().getName().startsWith("android.webkit.")) {
//            int[] sourceCoordinates = new int[2];
//            v.getLocationOnScreen(sourceCoordinates);
//            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
//            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];
//
//            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
//                hideKeyboard(this);
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void hideKeyboard(Activity activity) {
//        if (activity != null && activity.getWindow() != null) {
//            activity.getWindow().getDecorView();
//            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm != null) {
//                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
//            }
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.thong_ke_chi_tiet_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//            case R.id.mnChooseDate:
//                Calendar calendarDate= Calendar.getInstance();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        calendarDate.set(Calendar.YEAR,i);
//                        calendarDate.set(Calendar.MONTH,i1);
//                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
//                    }
//                };
//                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongKeThuChiTietActivity.this,callBack,
//                        calendarDate.get(Calendar.YEAR),
//                        calendarDate.get(Calendar.MONTH),
//                        calendarDate.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//                break;
//            default:break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onResume() {
//        loadData();
//        super.onResume();
//    }
//
//    private void linkViews() {
//        lvThongKeThuChiTiet = findViewById(R.id.lvThongKeThuChiTiet);
//    }
//    private void prepareDb() {
//        db = new MyDatabaseHelper(this);
//        db.createSomeThongKeThuChiTietData();
//    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private List<ThuChiActivity> getDataFromDb(){
//        InfoTKThuChiTiet = new ArrayList<>();
//        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THONG_KE_THU_CHI_TIET + " WHERE " + MyDatabaseHelper.COL_TKTHUCHITIET_THELOAI + " = '" + selectedThongKeThu.getInfoCategory() + "'");
//        InfoTKThuChiTiet.clear();
//        while (cursor.moveToNext()){
//            InfoTKThuChiTiet.add(new ThongKeChiTiet(cursor.getString(0), cursor.getString(1), cursor.getDouble(2), LocalDate.parse(cursor.getString(3))));
//        }
//        cursor.close();
//        return InfoTKThuChiTiet;
//    }
//
//    private void getData() {
//        Intent intent = getIntent();
//        selectedThongKeThu = (ThongKe) intent.getSerializableExtra("Thong Ke");
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void loadData() {
//        thuChiTietAdapter = new ThongKeChiTietAdapter(ThongKeThuChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, getDataFromDb());
//        lvThongKeThuChiTiet.setAdapter(thuChiTietAdapter);
//    }
//
//    private void addEvents() {
//        lvThongKeThuChiTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ThongKeThuChiTietActivity.this, ThongKeChinhSua.class);
//                thuChiTietAdapter = new ThongKeChiTietAdapter(ThongKeThuChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, InfoTKThuChiTiet);
//                ThongKeChiTiet thongKeChiTiet = (ThongKeChiTiet) thuChiTietAdapter.getItem(i);
//                intent.putExtra("ThongKeChiTiet", thongKeChiTiet);
//                startActivity(intent);
//            }
//        });
//    }
//}
