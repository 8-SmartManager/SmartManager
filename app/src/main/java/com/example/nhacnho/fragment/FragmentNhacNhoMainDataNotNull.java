package com.example.nhacnho.fragment;

import static com.example.smartmanagertwo.NhacNhoActivity.nhacNhos;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.nhacnho.NhacNho;
import com.example.nhacnho.NhacNhoAdapter;
import com.example.smartmanagertwo.NhacNhoActivity;
import com.example.smartmanagertwo.NhacNhoChiTietActivity;
import com.example.smartmanagertwo.R;

public class FragmentNhacNhoMainDataNotNull extends Fragment {
    NhacNhoAdapter adapter;
    ListView lvNhacNho;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhacnho_main_list_is_not_null,container,false);
        lvNhacNho=view.findViewById(R.id.lvNhacNho);
        addEvent();
        return view;
    }

    private void addEvent() {
        lvNhacNho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), NhacNhoChiTietActivity.class);
                adapter= new NhacNhoAdapter(getActivity(),R.layout.nhac_nho_item_layout,NhacNhoActivity.nhacNhos);
                NhacNho nhacNho= (NhacNho) adapter.getItem(i);
                intent.putExtra("Nhac Nho",nhacNho);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        adapter = new NhacNhoAdapter(getActivity(),R.layout.nhac_nho_item_layout, nhacNhos);
        lvNhacNho.setAdapter(adapter);
    }
}
