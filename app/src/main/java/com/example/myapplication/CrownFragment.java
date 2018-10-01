package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;


public class CrownFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView=(RecyclerView)container.findViewById(R.id.roomRecycle);
        recyclerView.setAdapter(new RoomRecycleAdapter(this.getContext()));

        return inflater.inflate(R.layout.fragment_crown, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event



}
