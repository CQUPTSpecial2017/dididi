package com.example.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.ImageDetail;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 爱学习的呆子熹 on 2018/9/30.
 */

public class RingView extends View  {
    private int currentItem=5 ;
    private int[] imageId = {
            R.drawable.denghao,R.drawable.jiezhi,R.drawable.aixin, R.drawable.huazhuangp, R.drawable.huangguan,
            R.drawable.huangguan2,R.drawable.zuanshi,R.drawable.yifu,R.drawable.youxiji,R.drawable.xiangshui,R.drawable.xiezi
            ,R.drawable.denghao
    };
    private List<Integer> showImageId = new ArrayList<>();
    private int showNumber =11;
    private Context context;
    private boolean isLeft=false,isRight=false;
    //View默认最小宽度
    private static final int DEFAULT_MIN_WIDTH = 400;
    private int width,height;
    private int bigItemWidthDp,bigItemHeightDp;
    private int allWidthDp,allHeightDp,itemWidthDp,itemHeightDp;
    private float top,bottom,left,right;
    private ImageDetail currentDetail = new ImageDetail();
    //背景
    private boolean isFirst = false;
    //外圆
    private Canvas mCanvas;
    private Paint paint;
    private boolean lastIsLeft = false;
    private boolean lastRight = false;

    //动画时间
    private int animTime = 1000;
    //显示的点位置
    private List<Float> xLocations = new ArrayList<>();
    private List<Float> yLocations = new ArrayList<>();
    private List<Float> scaleWidths = new ArrayList<>();
    private List<Float> scaleHeights = new ArrayList<>();
    private List<Float> changeXLocations = new ArrayList<>();
    private List<Float> changeYLocations = new ArrayList<>();
    private List<Float> changeScaleWidths = new ArrayList<>();
    private List<Float> changeScaleHeights = new ArrayList<>();
    private List<Float> mxLocations = new ArrayList<>();
    private List<Float> myLocations = new ArrayList<>();
    private ValueAnimator animation;

    public float getMyWidth(){
        return width;
    }

    private void initShowImageId(){
        if (!isFirst){

            for (int i = 0; i < showNumber; i++) {
                showImageId.add(imageId[i]);
            }
            showImageId.add(imageId[0]);
        }
        else {

            if (isLeft){
                currentItem=(currentItem+12)%11;
                if (!lastIsLeft){

                }
                else {
                    int id =showImageId.get(1);
                    for (int i = 0; i <showNumber ; i++) {
                        showImageId.set(i,showImageId.get(i+1));
                    }
                    showImageId.set(showNumber,id);
                }

                isLeft = false;
                lastIsLeft = true;
            }
            else if (isRight){
                currentItem=(currentItem+10)%11;
                if (lastIsLeft){
                }else {
                    int id = showImageId.get(showNumber - 1);
                    for (int i = showImageId.size() - 1; i > 0; i--) {
                        showImageId.set(i, showImageId.get(i - 1));
                    }
                    showImageId.set(0, id);
                }
                lastIsLeft = false;
                isRight = false;

            }

        }
    }

    private void  initLocations(){
        float x,y;
        itemHeightDp=35;
        itemWidthDp=35;
        bigItemHeightDp = 50;
        bigItemWidthDp = 50;
        for (int i = 0; i <showNumber ; i++) {
            if (i<5)
                x= i * dip2px(context,(allWidthDp/2-30)/5);
            else if (i>5)
                x= width/2 + (i-5)*dip2px(context,(allWidthDp/2-40)/5) ;
            else
                x= dip2px(context,(allWidthDp/2-bigItemWidthDp/2)) ;

            if (i<5)
                y=dip2px(context, i  * 3);
            else if (i>5)
                y=dip2px(context, 24 - i * 2);
            else
                y=dip2px(context, 15);
            xLocations.add(x);
            yLocations.add(y);
            changeXLocations.add(x);
            changeYLocations.add(y);
        }
        mxLocations.add(-(float)dip2px(context,20));
        myLocations.add(0f);
        //预留给左右滑动时新加入的图片

        xLocations.add(showNumber,width+(float)dip2px(context,20));
        yLocations.add(showNumber,0f);
        changeXLocations.add(showNumber,width+(float)dip2px(context,20));
        changeYLocations.add(showNumber,0f);
        for (int i = 1; i <xLocations.size() ; i++) {
            mxLocations.add(i,xLocations.get(i-1));
            myLocations.add(i,yLocations.get(i-1));
        }

    }
    public RingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }


    //初始化
    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        resetParams();
        if (!isFirst) {

            initShowImageId();
            //画初始图像
            drawBackRound(mCanvas);
            isFirst = !isFirst;

        }
        else {
            drawChange(canvas);
        }

    }
    private void drawChange(Canvas canvas) {
        initPaint();
        paint.setAntiAlias(true);
        //杆
        float scaleWidth1,scaleHeight1;
        Bitmap OrgBitmap2=BitmapFactory.decodeResource(getResources(), R.drawable.di);
        scaleWidth1=(float) getWidth() / OrgBitmap2.getWidth();
        scaleHeight1=(float) dip2px(context, 20) / OrgBitmap2.getHeight();

        Matrix matrix2 = new Matrix();

        matrix2.postScale(scaleWidth1,scaleHeight1);
        Bitmap resizedBitmap2 = Bitmap.createBitmap(OrgBitmap2, 0, 0, OrgBitmap2.getWidth(),
                OrgBitmap2.getHeight(), matrix2, true);
        canvas.drawBitmap(resizedBitmap2,
                0,0, paint);
        //图
        for (int i = 0; i < xLocations.size(); i++) {
            Bitmap OrgBitmap = BitmapFactory.decodeResource(getResources(), showImageId.get(i));
            Matrix matrix = new Matrix();
            matrix.postScale(currentDetail.getScaleWidths().get(i), currentDetail.getScaleHeights().get(i));
            Bitmap resizedBitmap = Bitmap.createBitmap(OrgBitmap, 0, 0, OrgBitmap.getWidth(),
                    OrgBitmap.getHeight(), matrix, true);
            canvas.drawBitmap(resizedBitmap,
                        currentDetail.getxLocations().get(i), currentDetail.getyLocations().get(i), paint);
        }
        //brands
        float scaleWidth,scaleHeight;
        Bitmap OrgBitmap1=
                BitmapFactory.decodeResource(getResources(), R.drawable.brands);
        scaleWidth=(float) dip2px(context, 80) / OrgBitmap1.getWidth();
        scaleHeight=(float) dip2px(context, 30) / OrgBitmap1.getHeight();

        Matrix matrix1 = new Matrix();

        matrix1.postScale(scaleWidth,scaleHeight);
        Bitmap resizedBitmap1 = Bitmap.createBitmap(OrgBitmap1, 0, 0, OrgBitmap1.getWidth(),
                OrgBitmap1.getHeight(), matrix1, true);
        canvas.drawBitmap(resizedBitmap1,
                xLocations.get(5)-dip2px(context,15),0, paint);
    }


    //画背景圆
    private void drawBackRound(Canvas canvas) {
        initPaint();

        top = 0;
        bottom = height;
        left =0;
        right = width;
        initLocations();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        float scaleWidth1,scaleHeight1;
        Bitmap OrgBitmap2=BitmapFactory.decodeResource(getResources(), R.drawable.di);
        scaleWidth1=(float) getWidth() / OrgBitmap2.getWidth();
        scaleHeight1=(float) dip2px(context, 20) / OrgBitmap2.getHeight();
        Matrix matrix2 = new Matrix();
        matrix2.postScale(scaleWidth1,scaleHeight1);
        Bitmap resizedBitmap2 = Bitmap.createBitmap(OrgBitmap2, 0, 0, OrgBitmap2.getWidth(),
                OrgBitmap2.getHeight(), matrix2, true);
        canvas.drawBitmap(resizedBitmap2,
                0,0, paint);



        for (int i = 0; i <xLocations.size() ; i++) {
            Bitmap OrgBitmap=BitmapFactory.decodeResource(getResources(), imageId[i]);
            if (i!=5) {
                scaleWidths.add(i,(float) dip2px(context, itemWidthDp) / OrgBitmap.getWidth());
                scaleHeights.add(i,(float) dip2px(context, itemHeightDp) / OrgBitmap.getHeight());
                changeScaleWidths.add(i,(float) dip2px(context, itemWidthDp) / OrgBitmap.getWidth());
                changeScaleHeights.add(i,(float) dip2px(context, itemHeightDp) / OrgBitmap.getHeight());
            }else {
                scaleWidths.add(i,(float) dip2px(context, bigItemWidthDp) / OrgBitmap.getWidth());
                scaleHeights.add(i,(float) dip2px(context, bigItemHeightDp) / OrgBitmap.getHeight());
                changeScaleWidths.add(i,(float) dip2px(context, bigItemHeightDp) / OrgBitmap.getWidth());
                changeScaleHeights.add(i,(float) dip2px(context, bigItemHeightDp) / OrgBitmap.getHeight());
            }

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidths.get(i),scaleHeights.get(i));
            Bitmap resizedBitmap = Bitmap.createBitmap(OrgBitmap, 0, 0, OrgBitmap.getWidth(),
                    OrgBitmap.getHeight(), matrix, true);
            canvas.drawBitmap(resizedBitmap,
                    xLocations.get(i), yLocations.get(i), paint);

        }
        float scaleWidth,scaleHeight;
        Bitmap OrgBitmap1=BitmapFactory.decodeResource(getResources(), R.drawable.brands);
        scaleWidth=(float) dip2px(context, 80) / OrgBitmap1.getWidth();
        scaleHeight=(float) dip2px(context, 30) / OrgBitmap1.getHeight();

        Matrix matrix1 = new Matrix();

        matrix1.postScale(scaleWidth,scaleHeight);
        Bitmap resizedBitmap1 = Bitmap.createBitmap(OrgBitmap1, 0, 0, OrgBitmap1.getWidth(),
                OrgBitmap1.getHeight(), matrix1, true);
        canvas.drawBitmap(resizedBitmap1,
                xLocations.get(5)-dip2px(context,15),0, paint);
        changeScaleHeights.add(showNumber,0.1f);
        changeScaleWidths.add(showNumber,0.1f);
        scaleHeights.add(showNumber,0.1f);
        scaleWidths.add(showNumber,0.1f);

    }

    public int startLeftAnimation(float postX,float curX){
        currentItem+=1;
        currentItem%=10;
        isLeft =true;
        initShowImageId();
        for (int i = 0; i <xLocations.size() ; i++) {
            Bitmap OrgBitmap=BitmapFactory.decodeResource(getResources(), showImageId.get(i));
            if (i!=5) {
                scaleWidths.set(i,(float) dip2px(context, itemWidthDp) / OrgBitmap.getWidth());
                scaleHeights.set(i,(float) dip2px(context, itemHeightDp) / OrgBitmap.getHeight());

            }else {
                scaleWidths.set(i,(float) dip2px(context, bigItemWidthDp) / OrgBitmap.getWidth());
                scaleHeights.set(i,(float) dip2px(context, bigItemHeightDp) / OrgBitmap.getHeight());

            }
        }

        ImageDetail startDetail = new ImageDetail();
        startDetail.setxLocations(xLocations);
        startDetail.setyLocations(yLocations);
        startDetail.setScaleWidths(scaleWidths);
        startDetail.setScaleHeights(scaleHeights);
        ImageDetail endDetail = new ImageDetail();

        for (int i = xLocations.size()-1; i >0 ; i-- ){
            changeXLocations.set(i,xLocations.get(i-1));
            changeYLocations.set(i,yLocations.get(i-1));
        }
        for (int i = xLocations.size()-1; i >0 ; i-- ){
            Bitmap OrgBitmap=BitmapFactory.decodeResource(getResources(), showImageId.get(i));
            if (i!=6) {
                changeScaleWidths.set(i,(float) dip2px(context, itemWidthDp) / OrgBitmap.getWidth());
                changeScaleHeights.set(i,(float) dip2px(context, itemHeightDp) / OrgBitmap.getHeight());
            }else {
                changeScaleWidths.set(i,(float) dip2px(context, bigItemWidthDp) / OrgBitmap.getWidth());
                changeScaleHeights.set(i,(float) dip2px(context, bigItemHeightDp) / OrgBitmap.getHeight());
            }

        }
        changeScaleHeights.set(0,0.1f);
        changeScaleWidths.set(0,0.1f);
        changeXLocations.set(0,-(float)dip2px(context,20));
        changeYLocations.set(0,0f);


        endDetail.setScaleWidths(changeScaleWidths);
        endDetail.setxLocations(changeXLocations);
        endDetail.setyLocations(changeYLocations);
        endDetail.setScaleHeights(changeScaleHeights);



        animation = ValueAnimator.ofObject(new ImageEvaluator(),startDetail,endDetail);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentDetail = (ImageDetail)animation.getAnimatedValue();
                invalidate();
            }
        });
        animation.setDuration(500);
        animation.start();
        return currentItem;
    }

    public int startRightAnimation(float postX,float curX){

        isRight =true;
        initShowImageId();
        for (int i = 0; i <xLocations.size() ; i++) {
            Bitmap OrgBitmap=BitmapFactory.decodeResource(getResources(), showImageId.get(i));
            if (i!=6) {
                scaleWidths.set(i,(float) dip2px(context, itemWidthDp) / OrgBitmap.getWidth());
                scaleHeights.set(i,(float) dip2px(context, itemHeightDp) / OrgBitmap.getHeight());

            }else {
                scaleWidths.set(i,(float) dip2px(context, bigItemWidthDp) / OrgBitmap.getWidth());
                scaleHeights.set(i,(float) dip2px(context, bigItemHeightDp) / OrgBitmap.getHeight());

            }
        }

        ImageDetail startDetail = new ImageDetail();


        startDetail.setxLocations(mxLocations);
        startDetail.setyLocations(myLocations);
        startDetail.setScaleWidths(scaleWidths);
        startDetail.setScaleHeights(scaleHeights);
        ImageDetail endDetail = new ImageDetail();

        for (int i = 0; i <xLocations.size()-1 ; i++ ){
            changeXLocations.set(i,mxLocations.get(i+1));
            changeYLocations.set(i,myLocations.get(i+1));
        }
        for (int i = xLocations.size()-1; i >=0 ; i-- ){
            Bitmap OrgBitmap=BitmapFactory.decodeResource(getResources(), showImageId.get(i));
            if (i!=5) {
                changeScaleWidths.set(i,(float) dip2px(context, itemWidthDp) / OrgBitmap.getWidth());
                changeScaleHeights.set(i,(float) dip2px(context, itemHeightDp) / OrgBitmap.getHeight());
            }else {
                changeScaleWidths.set(i,(float) dip2px(context, bigItemWidthDp) / OrgBitmap.getWidth());
                changeScaleHeights.set(i,(float) dip2px(context, bigItemHeightDp) / OrgBitmap.getHeight());
            }

        }
        changeScaleHeights.set(showNumber,0.1f);
        changeScaleWidths.set(showNumber,0.1f);
        changeXLocations.set(showNumber,width+(float)dip2px(context,20));
        changeYLocations.set(showNumber,0f);


        endDetail.setScaleWidths(changeScaleWidths);
        endDetail.setxLocations(changeXLocations);
        endDetail.setyLocations(changeYLocations);
        endDetail.setScaleHeights(changeScaleHeights);



        animation = ValueAnimator.ofObject(new ImageEvaluator(),startDetail,endDetail);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentDetail = (ImageDetail)animation.getAnimatedValue();
                invalidate();
            }
        });
        animation.setDuration(500);
        animation.start();
        lastIsLeft = false;
        return currentItem;
    }

    //初始化画笔
    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    //获取高宽
    private void resetParams() {
        width = getWidth();
        height = getHeight();
        allHeightDp = px2dip(context,height);
        allWidthDp = px2dip(context,width);
    }
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public int px2dip(Context context,float pxValue){
        final float scale = context.getResources ().getDisplayMetrics ().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }
}