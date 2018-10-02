package com.example.myapplication;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 爱学习的呆子熹 on 2018/10/2.
 */

@SuppressLint("AppCompatCustomView")
public class PaintView extends ImageView {
    private float scaleWidth,scaleHeight;
    private Paint paint;
    private Canvas mCanvas;
    private LoveLocation currentLocation ;
    private boolean isChange= false;
    private Context mContext;
    private ValueAnimator animation;
    private Rect rect = new Rect();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas=canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        if (isChange)
        drawBackground(mCanvas, currentLocation.getX(), currentLocation.getY());
        isChange = false;
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

    public void startMyAnimation(float width, int[] location){

        currentLocation = new LoveLocation(location[0],location[1]);
        animation= ValueAnimator.ofObject(new LoveEvaluator(),new LoveLocation(location[0],location[1]-dip2px(mContext, 20))
                ,new LoveLocation(width/2,dip2px(mContext, 50)));
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentLocation= (LoveLocation)animation.getAnimatedValue();
                if (currentLocation.getX()==0f&&currentLocation.getY()==0f){
                    isChange = false;
                }else {
                    isChange= true;
                }
                invalidate();
            }
        });
        isChange = true;
        animation.setDuration(750);

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
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }
}
