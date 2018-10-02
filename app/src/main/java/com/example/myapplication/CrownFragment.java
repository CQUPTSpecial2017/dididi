package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;


public class CrownFragment extends Fragment {


    private View view;

    private float width;
    private PaintView paintView;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crown, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RoomRecycleAdapter adapter = new RoomRecycleAdapter(this.getContext());
        adapter.setPaintView(paintView);
        adapter.setWidth(width);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setPaintView(PaintView paintView) {
        this.paintView = paintView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }
    // TODO: Rename method, update argument and hook method into UI event



}
