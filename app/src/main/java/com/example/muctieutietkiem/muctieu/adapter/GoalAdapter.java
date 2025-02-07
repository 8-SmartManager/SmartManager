package com.example.muctieutietkiem.muctieu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.widget.ImageViewCompat;

import com.example.muctieutietkiem.muctieu.model.Goal;
import com.example.smartmanagertwo.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class GoalAdapter extends BaseAdapter {

    Context context;
    int item_layout;
    List<Goal> goalList;

    public GoalAdapter(Context context, int item_layout, List<Goal> goalList) {
        this.context = context;
        this.item_layout = item_layout;
        this.goalList = goalList;
    }

    @Override
    public int getCount() {
        return goalList.size();
    }

    @Override
    public Object getItem(int i) {
        return goalList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout,null);
            holder.imvGoalThumb= view.findViewById(R.id.imvGoal);
            holder.goalName=view.findViewById(R.id.txtGoal);
            holder.goalTime=view.findViewById(R.id.txtDate);
            holder.progressBar=view.findViewById(R.id.progressBar);
            holder.goalTarget=view.findViewById(R.id.txtGoalTarget);
            holder.goalSaved=view.findViewById(R.id.txtGoalSaved);

            view.setTag(holder);
        }
        else {holder= (ViewHolder) view.getTag();
        }
        Goal g = goalList.get(i);

        holder.imvGoalThumb.setImageResource(g.getGoalThumb());
        holder.goalName.setText(g.getGoalName());
        holder.goalTime.setText(("Ngày đạt: "+g.getGoalTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        double percent = (g.getGoalSaved()/g.getGoalTarget())*100;
        holder.goalTarget.setText("Mục tiêu: "+String.format("%,.0f",g.getGoalTarget())+"đ");
        holder.goalSaved.setText("Đã tiết kiệm: "+String.format("%,.0f",g.getGoalSaved())+"đ");
        holder.progressBar.setProgress((int) percent);
        ImageViewCompat.setImageTintList(holder.imvGoalThumb, ColorStateList.valueOf(g.getGoalColor()));

        return view;
    }
    public static class ViewHolder{
        ImageView imvGoalThumb;
        TextView goalName,goalTime,goalTarget,goalSaved;
        ProgressBar progressBar;
    }
}
