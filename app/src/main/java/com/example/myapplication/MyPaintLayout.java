package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by 爱学习的呆子熹 on 2018/10/2.
 */

public class MyPaintLayout extends RelativeLayout {
    private float scaleWidth,scaleHeight;
    private Paint paint;
    private Canvas mCanvas;
    private LoveLocation currentLocation ;
    private boolean isFirst= true;
    private Context mContext;
    private ValueAnimator animation;
    private Rect rect = new Rect();


    public MyPaintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        isFirst = false;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas=canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        drawBackground(mCanvas, currentLocation.getX(), currentLocation.getY());
    }

    private void drawBackground(Canvas canvas, float x, float y){
        Bitmap OrgBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.love );
        scaleWidth = (float)dip2px(mContext, 20) / OrgBitmap.getWidth();
        scaleHeight = (float)dip2px(mContext, 30) / OrgBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(OrgBitmap, 0, 0, OrgBitmap.getWidth(),
                OrgBitmap.getHeight(), matrix, true);
        canvas.drawBitmap(resizedBitmap,
                x, y, paint);
    }

    public void startMyAnimation(float width, Rect rect){
        this.rect = rect;
        currentLocation = new LoveLocation(rect.left,rect.top);
        animation= ValueAnimator.ofObject(new LoveEvaluator(),new LoveLocation(rect.left,rect.top)
                ,new LoveLocation(width/2,0));
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentLocation= (LoveLocation)animation.getAnimatedValue();
                invalidate();
            }
        });
        animation.setDuration(1000);
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
