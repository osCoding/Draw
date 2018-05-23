package com.cyhz.drawgraph.drawgraph.draw.model;

/**
 * Created by qinghua on 2015/8/7.
 */
public class PathXY {
    private float x;
    private float y;

    public PathXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PathXY() {
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

    @Override
    public String toString() {
        return "PathXY{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
