package com.cyhz.drawgraph.drawgraph.draw.model;

import android.graphics.Bitmap;

/**
 * Created by qinghua on 2015/8/7.
 */
public class BitmapModel extends DrawBaseModel {
    private Bitmap bitmap;
    private float beginX;
    private float beginY;
    private float width;
    private float height;

    public BitmapModel() {
    }

    public BitmapModel(Bitmap bitmap, float width,float height,float beginX, float beginY) {
        this.bitmap = bitmap;
        this.beginX = beginX;
        this.beginY = beginY;
        this.width = width;
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getBeginX() {
        return beginX;
    }

    public void setBeginX(float beginX) {
        this.beginX = beginX;
    }

    public float getBeginY() {
        return beginY;
    }

    public void setBeginY(float beginY) {
        this.beginY = beginY;
    }
}
