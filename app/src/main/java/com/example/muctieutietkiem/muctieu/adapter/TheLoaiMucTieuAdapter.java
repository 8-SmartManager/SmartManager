package com.example.muctieutietkiem.muctieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muctieutietkiem.muctieu.model.TheLoai;
import com.example.smartmanagertwo.R;

import java.util.List;

public class TheLoaiMucTieuAdapter extends BaseAdapter {

    private Context context;
    private int item_theloai;
    private List<TheLoai> theLoaiList;

    public TheLoaiMucTieuAdapter(Context context, int item_theloai, List<TheLoai> theLoaiList) {
        this.context = context;
        this.item_theloai = item_theloai;
        this.theLoaiList = theLoaiList;
    }

    @Override
    public int getCount() {
        return theLoaiList.size();
    }

    @Override
    public Object getItem(int i) {
        return theLoaiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TheLoaiMucTieuAdapter.ViewHolder holder;
        if (view==null)
        {
            holder=new TheLoaiMucTieuAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_theloai,null);
            holder.imvTheLoaiIcon= view.findViewById(R.id.imvTheLoaiIcon);
            holder.txtItemTenTheLoai=view.findViewById(R.id.txtItemTenTheLoai);


            view.setTag(holder);
        }
        else {holder= (TheLoaiMucTieuAdapter.ViewHolder) view.getTag();
        }
        TheLoai tl = theLoaiList.get(i);
        holder.imvTheLoaiIcon.setImageResource(tl.getIcon());
        holder.txtItemTenTheLoai.setText(tl.getTenTheLoai());


        return view;
    }
    public static class ViewHolder{
        ImageView imvTheLoaiIcon;
        TextView txtItemTenTheLoai;

    }
}
