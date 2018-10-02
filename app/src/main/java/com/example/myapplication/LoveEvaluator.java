package com.example.myapplication;

import android.animation.TypeEvaluator;
import android.location.Location;

/**
 * Created by 爱学习的呆子熹 on 2018/10/1.
 */

public class LoveEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        LoveLocation s = (LoveLocation) startValue;
        LoveLocation e = (LoveLocation) endValue;
        float x, y;
        if (e.getX() > s.getX())
            x = (e.getX() - s.getX()) * fraction + s.getX();
        else
            x = s.getX() - (s.getX() - e.getX()) * fraction;

        y = s.getY() - (s.getY() - e.getY()) * fraction*fraction;
            if (fraction == 1f) {
                return new LoveLocation(0f, 0f);
            }
            return new LoveLocation(x, y);
    }
}

