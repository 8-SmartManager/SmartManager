package com.example.muctieutietkiem.packages.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.muctieutietkiem.packages.model.ImageMucTieu;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    Context context;
    int item_layout;
    List<ImageMucTieu> images;
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(item_layout,null);
            holder.imvThumb=view.findViewById(R.id.imvThumbImage);

            view.setTag(holder);
        }
        else {
            holder= (ViewHolder) view.getTag();
        }
        ImageMucTieu image= images.get(i);
        //Convert byte array> bitmap

        holder.imvThumb.setImageResource(image.getImageId());
        return view;

    }
    public static class ViewHolder{
        ImageView imvThumb;}
}
