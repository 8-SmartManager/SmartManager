package com.example.muctieutietkiem.muctieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.muctieutietkiem.muctieu.model.Color;
import com.example.smartmanagertwo.R;

import java.util.List;

public class ColorAdapter extends BaseAdapter {

    Context context;
    int item_color;
    List<Color> colorList;

    public ColorAdapter(Context context, int item_layout, List<Color> colors) {
        this.context = context;
        this.item_color= item_layout;
        this.colorList = colors;
    }

    @Override
    public int getCount() {
        return colorList.size();
    }

    @Override
    public Object getItem(int i) {
        return colorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ColorAdapter.ViewHolder holder;
        if (view==null)
        {
            holder=new ColorAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_color,null);
            holder.imvColor= view.findViewById(R.id.imvColor);



            view.setTag(holder);
        }
        else {holder= (ColorAdapter.ViewHolder) view.getTag();
        }
        Color color = colorList.get(i);
        holder.imvColor.setImageResource(color.getColorThumb());
        return view;
    }

    public static class ViewHolder{
        ImageView imvColor;}
}
