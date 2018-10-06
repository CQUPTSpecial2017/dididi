package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 爱学习的呆子熹 on 2018/10/2.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoHolder> {
    Context context;
    @Override
    public DemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);

        return new DemoHolder(v);
    }
    public DemoAdapter(Context context) {
        this.context = context;
    }
    @Override
    public void onBindViewHolder(DemoHolder holder, int position) {
        Glide.with(context).load(R.drawable.catchpin).into(holder.catchb);
        Glide.with(context).load(R.drawable.catchpin).into(holder.catchc);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class DemoHolder extends RecyclerView.ViewHolder {

        ImageView catchb;
        ImageView catchc;

        DemoHolder(View itemView) {
            super(itemView);
            catchb=(ImageView)itemView.findViewById(R.id.catchb);
            catchc=(ImageView)itemView.findViewById(R.id.catchc);

        }
    }
}
