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

    private Context context;
    private PaintView paintView;
    private float width;

    public void setPaintView(PaintView paintView) {
        this.paintView = paintView;
    }

    public void setWidth(float width) {

        this.width = width;
    }

    public RoomRecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new RoomHolder(v);
    }

    @Override
    public void onBindViewHolder(RoomHolder holder, int position) {
        Glide.with(context).load(R.drawable.catchpin).into(holder.catchb);
        Glide.with(context).load(R.drawable.catchpin).into(holder.catchc);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RoomHolder extends RecyclerView.ViewHolder {
        float x,y;
        ImageView love1;
        ImageView love2;
        ImageView catchc;
        ImageView catchb;

        RoomHolder(View itemView) {
            super(itemView);
            catchb=(ImageView)itemView.findViewById(R.id.catchb);
            catchc=(ImageView)itemView.findViewById(R.id.catchc);

            love1=(ImageView)itemView.findViewById(R.id.love1);
            love2=(ImageView)itemView.findViewById(R.id.love2);

            love1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int[] location = new int[2];
                    love1.getLocationOnScreen(location);
                    paintView.startMyAnimation(width,location);
                    paintView.bringToFront();

                    return false;
                }
            });
            love2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int[] location = new int[2];
                    love2.getLocationOnScreen(location);
                    paintView.startMyAnimation(width,location);
                    paintView.bringToFront();

                    return false;
                }
            });

        }
    }

}

