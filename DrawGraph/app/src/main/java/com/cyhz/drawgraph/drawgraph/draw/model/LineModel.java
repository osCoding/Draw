package com.cyhz.drawgraph.drawgraph.draw.model;

/**
 * Created by qinghua on 2015/8/7.
 */
public class LineModel extends DrawBaseModel {

    private float beginX;
    private float beginY;
    private float endX;
    private float endY;

    public LineModel(float beginX, float beginY,float endX, float endY,int color,int strokeWidth) {
        this.beginX = beginX;
        this.endX = endX;
        this.beginY = beginY;
        this.endY = endY;
        this.setColor(color);
        this.setStrokeWidth(strokeWidth);
    }

    public float getBeginX() {
        return beginX;
    }

    public void setBeginX(float beginX) {
        this.beginX = beginX;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getBeginY() {
        return beginY;
    }

    public void setBeginY(float beginY) {
        this.beginY = beginY;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }
}
