package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 爱学习的呆子熹 on 2018/10/2.
 */

public class DemoFragment extends android.support.v4.app.Fragment {
    private View view;


    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crown, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DemoAdapter adapter = new DemoAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }
}
