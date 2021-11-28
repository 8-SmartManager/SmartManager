package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.ActivityAdapter;
import com.example.model.ThuChiActivity;

import java.util.ArrayList;
import java.util.List;

public class ThuChi extends AppCompatActivity {

    public static MyDatabaseHelper db;

    TextView txtActivityName, txtActivityAccount, txtActivityAmount;
    ListView lvActivity;
    ActivityAdapter adapter;
    ArrayList<ThuChiActivity> activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_chi_chinh);

        prepareDb();
        linkViews();
        loadData();

    }

    private void prepareDb() {
        db = new MyDatabaseHelper(this);
        db.createSomeData();
    }

    private void loadData() {

        adapter = new ActivityAdapter(ThuChi.this, R.layout.item_thuchi, getDataFromDb());
        lvActivity.setAdapter(adapter);
    }

    private List<ThuChiActivity> getDataFromDb() {
        activity = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THUCHI);
        activity.clear();
        while(cursor.moveToNext()){
//            activity.add(new ThuChiActivity(cursor.getInt(0), cursor.getString(1)));
            activity.add(new ThuChiActivity(cursor.getString(0), cursor.getString(1), cursor.getDouble(2)));
        }
        cursor.close();
        return activity;
    }

    private void linkViews() {
        lvActivity = findViewById(R.id.lvActivity);

    }
}