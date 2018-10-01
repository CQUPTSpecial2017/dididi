package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;



import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private float mPosX,mPosY,mCurPosX,mCurPosY;
    private RingView ringView;
    private ImageView leftImage,centerImage,rightImage;
    private int imgIds[]={R.drawable.aixin, R.drawable.huangguan, R.drawable.huangguan2, R.drawable.jiezhi,R.drawable.xiezi,
            R.drawable.yifu, R.drawable.huazhuangp, R.drawable.youxiji,R.drawable.zhuanlun, R.drawable.zuanshi};
    private LoveImageView love1;
    private ImageView love2;
    private ImageView love3;
    private ImageView catchc;
    private ImageView catchb;
    private ImageView catchd;
    private float x,y,width,heigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        ringView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
//                        if (mCurPosY - mPosY > 0
//                                && (Math.abs(mCurPosY - mPosY) > 25)) {
//                            //向下滑動
//                            tiShi(mContext,"向下");
//
//                        } else if (mCurPosY - mPosY < 0
//                                && (Math.abs(mCurPosY - mPosY) > 25)) {
//                            //向上滑动
//                            tiShi(mContext,"向上");
//                        }
                        if (mCurPosX - mPosX > 0
                                && (Math.abs(mCurPosX - mPosX) > 25)) {
                            centerImage.setImageResource(imgIds[ringView.startRightAnimation(mPosX,mCurPosX)]);

                        } else if (mCurPosX - mPosX < 0
                                && (Math.abs(mCurPosX - mPosX) > 25)) {
                            centerImage.setImageResource(imgIds[ringView.startLeftAnimation(mPosX,mCurPosX)]);
                        }
                        break;
                }
                return true;
            }

        });




    }
    private void initView(){
        ringView = (RingView)findViewById(R.id.first_ring);
        leftImage = (ImageView)findViewById(R.id.first_image_1);
        centerImage = (ImageView)findViewById(R.id.first_image_2);
        rightImage = (ImageView)findViewById(R.id.first_image_3);
        catchb=(ImageView)findViewById(R.id.catchb);
        catchc=(ImageView)findViewById(R.id.catchc);
        catchd=(ImageView)findViewById(R.id.catchd);
        love1 = (LoveImageView) findViewById(R.id.love1);


        Glide.with(this).load(R.drawable.catchpin).into(catchb);
        Glide.with(this).load(R.drawable.catchpin).into(catchc);
        Glide.with(this).load(R.drawable.catchpin).into(catchd);

        love1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                love1.startMyAnimation(event.getX(),event.getY(),ringView.getMyWidth());
                return false;
            }
        });

    }



}
