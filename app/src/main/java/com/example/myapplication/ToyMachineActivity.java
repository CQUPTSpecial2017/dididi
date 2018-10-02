package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;

public class ToyMachineActivity extends AppCompatActivity {

 ImageView continueb;
 ImageView video;
 ImageView backer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_machine);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        init();
    }
    public void  init(){


       backer=(ImageView)findViewById(R.id.backer);
       Glide.with(this).load(R.drawable.backer).into(backer);
        continueb=(ImageView)findViewById(R.id.continueBlue);
        Glide.with(this).load(R.drawable.continuebl).into(continueb);
        continueb.bringToFront();
        continueb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ToyMachineActivity.this,FirstActivity.class);
                startActivity(intent);

            }
        });

    }
}
