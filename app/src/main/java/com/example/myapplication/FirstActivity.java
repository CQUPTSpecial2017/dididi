package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;



import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private float mPosX,mPosY,mCurPosX,mCurPosY;
    private RingView ringView;
    private ImageView centerImage;
    private int imgIds[]={R.drawable.aixin, R.drawable.huangguan, R.drawable.huangguan2, R.drawable.jiezhi,R.drawable.xiezi,
            R.drawable.yifu, R.drawable.huazhuangp, R.drawable.youxiji,R.drawable.denghao, R.drawable.zuanshi};
    private ImageView jinbi,xiong,time,zhuanlun,hua;
    private PaintView paint;
        private List<Fragment> mFragments;
    private ImageView leftLight,rightLight,leftCatch,spin,zhuanpan,watch,leftCircle;
    private CustomViewPager viewPager ;
    private float x,y,width,heigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        initListener();




    }
    private void initListener(){

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
                            int i;

                            i=ringView.startRightAnimation(mPosX,mCurPosX);
                            centerImage.setImageResource(imgIds[i]);
                            viewPager.setCurrentItem(i,true);

                        } else if (mCurPosX - mPosX < 0
                                && (Math.abs(mCurPosX - mPosX) > 25)) {
                            int i;

                            i=ringView.startLeftAnimation(mPosX,mCurPosX);
                            centerImage.setImageResource(imgIds[i]);
                            viewPager.setCurrentItem(i,true);
                }
                        break;
                }
                return true;
            }

        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==0){
                    viewPager.setCurrentItem(10,false);
                }
                else if (position==11){

                    viewPager.setCurrentItem(1,false);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==1) {//state有三种状态下文会将，当手指刚触碰屏幕时state的值为1，我们就在这个时候给mViewPagerIndex 赋值。

                }
            }
        });
        jinbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,EmptyActivity.class);
                startActivity(intent);
            }
        });
        xiong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,EmptyActivity.class);
                startActivity(intent);
            }
        });
        hua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,EmptyActivity.class);
                startActivity(intent);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,EmptyActivity.class);
                startActivity(intent);
            }
        });
        zhuanlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,EmptyActivity.class);
                startActivity(intent);
            }
        });

    }


    private void initView(){
        centerImage = (ImageView)findViewById(R.id.first_image_2);
        paint = (PaintView)findViewById(R.id.first_paint) ;
        leftLight = (ImageView)findViewById(R.id.left_light) ;
        rightLight = (ImageView)findViewById(R.id.right_light) ;
        leftCatch = (ImageView)findViewById(R.id.first_catch) ;
        spin = (ImageView)findViewById(R.id.first_spin) ;
        zhuanpan = (ImageView)findViewById(R.id.first_zhuanpan) ;
        watch = (ImageView)findViewById(R.id.watch_1) ;
        leftCircle = (ImageView)findViewById(R.id.dengguang) ;
        ringView =(RingView)findViewById(R.id.first_ring) ;
        jinbi = (ImageView)findViewById(R.id.first_jinbi);
        time = (ImageView)findViewById(R.id.first_time);
        xiong = (ImageView)findViewById(R.id.first_xiong);
        hua = (ImageView)findViewById(R.id.first_hua);
        zhuanlun = (ImageView)findViewById(R.id.first_zhuanlun);

        viewPager = (CustomViewPager) findViewById(R.id.first_viewpager);
        mFragments = new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            CrownFragment fragment = new CrownFragment();
            fragment.setPaintView(paint);
            fragment.setWidth(1080);
            mFragments.add(fragment);
        }
        for (int i = 6; i <12 ; i++) {
            DemoFragment fragment = new DemoFragment();
            mFragments.add(fragment);
        }
        MyViewpagerAdapter myViewpagerAdapter = new MyViewpagerAdapter(getSupportFragmentManager(),mFragments);

        viewPager.setAdapter(myViewpagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);

        Glide.with(this).load(R.drawable.left_light).into(leftLight);
        Glide.with(this).load(R.drawable.right_light).into(rightLight);
        Glide.with(this).load(R.drawable.catch_image).into(leftCatch);
        Glide.with(this).load(R.drawable.spin).into(spin);
        Glide.with(this).load(R.drawable.zhuanpan).into(zhuanpan);
        Glide.with(this).load(R.drawable.watch1).into(watch);
        Glide.with(this).load(R.drawable.dengguang).into(leftCircle);




    }
    private class MyViewpagerAdapter extends FragmentPagerAdapter{

        private List<Fragment>mFragments;

        public MyViewpagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = new ArrayList<>();
            mFragments.addAll(fragments);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


    }


}
