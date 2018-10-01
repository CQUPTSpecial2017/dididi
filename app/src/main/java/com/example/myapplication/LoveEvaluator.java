package com.example.myapplication;

import android.animation.TypeEvaluator;

/**
 * Created by 爱学习的呆子熹 on 2018/10/1.
 */

public class LoveEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        LoveLocation s= (LoveLocation)startValue;
        LoveLocation e= (LoveLocation)endValue;
        float x = (e.getX()-s.getX())*fraction + s.getX();
        float y = (s.getY()-e.getY())*(1-fraction)*(1-fraction)  ;

        return new LoveLocation(x,y);
    }
}
