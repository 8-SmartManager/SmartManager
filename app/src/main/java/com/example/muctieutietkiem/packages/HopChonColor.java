package com.example.muctieutietkiem.packages;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.DialogFragment;

import com.example.muctieutietkiem.packages.adapter.ColorAdapter;
import com.example.muctieutietkiem.packages.adapter.TheLoaiMucTieuAdapter;
import com.example.muctieutietkiem.packages.model.Color;
import com.example.muctieutietkiem.packages.model.TheLoai;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonColor extends DialogFragment {
    GridView gvColor;
    ArrayList<Color> colors;
    ColorAdapter adapter;
    ImageButton btnDismiss;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.hop_chon_chung, container, false);
        gvColor=view.findViewById(R.id.gvHopChonItem);
        btnDismiss = view.findViewById(R.id.btnDismiss);

        adapter = new ColorAdapter(getContext(),R.layout.item_color_muctieu,initData());
        gvColor.setAdapter(adapter);
        gvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ImageView imageView= getActivity().findViewById(R.id.imvColor);
                ImageView imvDrop= getActivity().findViewById(R.id.imvDrop);
                adapter = new ColorAdapter(getContext(),R.layout.item_color_muctieu,initData());
                Color color= (Color) adapter.getItem(i);
                imageView.setImageResource(color.getColorThumb());
                dismiss();
                ImageViewCompat.setImageTintList(imvDrop, null);
            }
            }
            );
        btnDismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imvDrop= getActivity().findViewById(R.id.imvDrop);
                    dismiss();
                    ImageViewCompat.setImageTintList(imvDrop, null);
                }
            });

        return view;

    }




    private ArrayList<Color> initData() {
        colors=new ArrayList<Color>();
        colors.add(new Color("Coral Pink",-430225,R.drawable.ic_coral_pink));
        colors.add(new Color("Pink",-48536,R.drawable.ic_pink));
        colors.add(new Color("Lime",-7082464,R.drawable.ic_lime));
        colors.add(new Color("Grey",-3881788,R.drawable.ic_grey));
        colors.add(new Color("Green",-11873872,R.drawable.ic_green));
        colors.add(new Color("Yellow",-256,R.drawable.ic_yellow));
        colors.add(new Color("Blue",-15163141,R.drawable.ic_blue));
        colors.add(new Color("Orange",-149741,R.drawable.ic_orange));
        colors.add(new Color("Purple",-3319318,R.drawable.ic_purple));


        return colors;

    }

}
