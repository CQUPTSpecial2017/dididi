package com.example.myapplication;

/**
 * Created by 爱学习的呆子熹 on 2018/10/1.
 */

public class LoveLocation {
    public LoveLocation() {

    }

    public LoveLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    private float x,y;
}
