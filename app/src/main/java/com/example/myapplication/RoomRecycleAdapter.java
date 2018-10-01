package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class RoomRecycleAdapter extends RecyclerView.Adapter<RoomRecycleAdapter.RoomHolder> {

    Context context;
    private static float width,height;

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public RoomRecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomHolder(v);
    }

    @Override
    public void onBindViewHolder(RoomHolder holder, int position) {
        Glide.with(context).load(R.drawable.catchpin).into(holder.catchb);
        Glide.with(context).load(R.drawable.catchpin).into(holder.catchc);
        Glide.with(context).load(R.drawable.catchpin).into(holder.catchd);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class RoomHolder extends RecyclerView.ViewHolder {
        float x,y;
        ImageView love1;
        ImageView love2;
        ImageView love3;
        ImageView catchc;
        ImageView catchb;
        ImageView catchd;
        RoomHolder(View itemView) {
            super(itemView);
            catchb=(ImageView)itemView.findViewById(R.id.catchb);
            catchc=(ImageView)itemView.findViewById(R.id.catchc);
            catchd=(ImageView)itemView.findViewById(R.id.catchd);
            love1=(ImageView)itemView.findViewById(R.id.love1);

            love1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ValueAnimator animation = ValueAnimator.ofObject(new LoveEvaluator(),new LoveLocation(x,y)
                    ,new LoveLocation(width/2,0));


                    animation.start();
                }
            });
            love1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    x = event.getRawX();
                    y = event.getRawY();
                    return false;
                }
            });
        }
    }
    public void  startAn(ImageView imageView){




    }
}

