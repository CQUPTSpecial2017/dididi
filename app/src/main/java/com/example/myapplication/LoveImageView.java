package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;


/**
 * Created by 爱学习的呆子熹 on 2018/10/1.
 */

public class LoveImageView extends android.support.v7.widget.AppCompatImageView {
    private float scaleWidth,scaleHeight;
    private Paint paint;
    private Canvas mCanvas;
    private LoveLocation currentLocation ;
    private boolean isFirst= true;
    private Context mContext;
    public LoveImageView(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas=canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        if (!isFirst){
            drawChange(mCanvas);
        }
        isFirst =false;
    }
    private void drawChange(Canvas canvas){
        Bitmap OrgBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.love );
        scaleWidth = dip2px(mContext, 30) / OrgBitmap.getWidth();
        scaleHeight = dip2px(mContext, 30) / OrgBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(OrgBitmap, 0, 0, OrgBitmap.getWidth(),
                OrgBitmap.getHeight(), matrix, true);
        canvas.drawBitmap(resizedBitmap,
                currentLocation.getX(), currentLocation.getY(), paint);
    }
    public void startMyAnimation(float x, float y,float width){
        currentLocation = new LoveLocation(x,y);

        ValueAnimator animation = ValueAnimator.ofObject(new LoveEvaluator(),new LoveLocation(x,y)
                ,new LoveLocation(width/2,0));
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentLocation= (LoveLocation)animation.getAnimatedValue();
                invalidate();
            }
        });
        animation.setDuration(500);
        animation.start();
    }
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public int px2dip(Context context,float pxValue){
        final float scale = context.getResources ().getDisplayMetrics ().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
