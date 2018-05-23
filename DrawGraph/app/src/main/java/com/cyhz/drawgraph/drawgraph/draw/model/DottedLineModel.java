package com.cyhz.drawgraph.drawgraph.draw.model;

/**
 * Created by qinghua on 2015/8/7.
 */
public class DottedLineModel extends DrawBaseModel{

    private float beginX;
    private float beginY;
    private float endX;
    private float endY;

    public DottedLineModel() {}

    public DottedLineModel(float beginX, float beginY, float endX, float endY,int color,int strokeWidth) {
        this.beginX = beginX;
        this.beginY = beginY;
        this.endX = endX;
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

    public float getBeginY() {
        return beginY;
    }

    public void setBeginY(float beginY) {
        this.beginY = beginY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }
}
