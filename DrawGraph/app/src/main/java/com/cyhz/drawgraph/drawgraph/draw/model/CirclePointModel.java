package com.cyhz.drawgraph.drawgraph.draw.model;

/**
 * Created by qinghua on 2015/8/25.
 */
public class CirclePointModel extends DrawBaseModel{

    private float cx;
    private float cy;
    private float bigCricleRadius;
    private int bigCricleColor;
    private float smallCricleRadius;
    private int smallCricleColor;

    public CirclePointModel(float cx, float cy, float bigCricleRadius, float smallCricleRadius, int bigCricleColor, int smallCricleColor) {
        this.cx = cx;
        this.cy = cy;
        this.bigCricleRadius = bigCricleRadius;
        this.smallCricleRadius = smallCricleRadius;
        this.bigCricleColor = bigCricleColor;
        this.smallCricleColor = smallCricleColor;
    }

    public int getBigCricleColor() {
        return bigCricleColor;
    }

    public void setBigCricleColor(int bigCricleColor) {
        this.bigCricleColor = bigCricleColor;
    }

    public int getSmallCricleColor() {
        return smallCricleColor;
    }

    public void setSmallCricleColor(int smallCricleColor) {
        this.smallCricleColor = smallCricleColor;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public float getBigCricleRadius() {
        return bigCricleRadius;
    }

    public void setBigCricleRadius(float bigCricleRadius) {
        this.bigCricleRadius = bigCricleRadius;
    }


    public float getSmallCricleRadius() {
        return smallCricleRadius;
    }

    public void setSmallCricleRadius(float smallCricleRadius) {
        this.smallCricleRadius = smallCricleRadius;
    }
}
