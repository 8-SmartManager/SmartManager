package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.XemActivityAdapter;
import com.example.model.ThuChiActivity;
import com.example.model.XemThuChiActivity;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class ThuChiNgay extends Fragment {

    GridView gvActivity;
    ArrayList<XemThuChiActivity> activities;
    XemActivityAdapter adapter;

    XemThuChiActivity selectedActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi_ngay, container, false);

        gvActivity = view.findViewById(R.id.gvActivity);

        initData();
        loadData();
        addEvents();

        return view;
    }

    private void addEvents() {
        gvActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void loadData() {
        adapter = new XemActivityAdapter(this.getActivity(),R.layout.custom_gridview_item_ngay, activities);
        gvActivity.setAdapter(adapter);
    }

    private void initData() {
        activities = new ArrayList<XemThuChiActivity>();
        activities.add(new XemThuChiActivity(1, 0,0));
        activities.add(new XemThuChiActivity(2, 0,0));
        activities.add(new XemThuChiActivity(3, 0,0));
        activities.add(new XemThuChiActivity(4, 0,0));
        activities.add(new XemThuChiActivity(5, 0,0));
        activities.add(new XemThuChiActivity(6, 0,0));
        activities.add(new XemThuChiActivity(7, 0,0));
        activities.add(new XemThuChiActivity(8, 0,0));
        activities.add(new XemThuChiActivity(9, 0,0));
        activities.add(new XemThuChiActivity(10, 0,0));
        activities.add(new XemThuChiActivity(11, 0,0));
        activities.add(new XemThuChiActivity(12, 0,0));
        activities.add(new XemThuChiActivity(13, 0,0));
        activities.add(new XemThuChiActivity(14, 0,0));
        activities.add(new XemThuChiActivity(15, 0,0));
        activities.add(new XemThuChiActivity(16, 0,0));
        activities.add(new XemThuChiActivity(17, 0,0));
        activities.add(new XemThuChiActivity(18, 2000000,2000000));
        activities.add(new XemThuChiActivity(19, 0,0));
        activities.add(new XemThuChiActivity(20, 0,0));
        activities.add(new XemThuChiActivity(21, 0,0));
        activities.add(new XemThuChiActivity(22, 0,0));
        activities.add(new XemThuChiActivity(23, 0,0));
        activities.add(new XemThuChiActivity(24, 0,0));
        activities.add(new XemThuChiActivity(25, 0,0));
        activities.add(new XemThuChiActivity(26, 0,0));
        activities.add(new XemThuChiActivity(27, 0,0));
        activities.add(new XemThuChiActivity(28, 0,0));
        activities.add(new XemThuChiActivity(29, 0,0));
        activities.add(new XemThuChiActivity(30, 0,0));


    }
}
