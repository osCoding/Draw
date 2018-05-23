package com.cyhz.drawgraph.drawgraph.draw.model;

import java.util.List;

/**
 * Created by qinghua on 2015/8/7.
 */
public class PathModel extends DrawBaseModel{
    private List<PathXY> pathXYList;
    private boolean isFill;

    public PathModel(List<PathXY> pathXYList,boolean isFill,int color,int strokeWidth) {
        this.pathXYList = pathXYList;
        this.isFill = isFill;
        this.setColor(color);
        this.setStrokeWidth(strokeWidth);
    }

    public PathModel() {
    }

    public List<PathXY> getPathXYList() {
        return pathXYList;
    }

    public void setPathXYList(List<PathXY> pathXYList) {
        this.pathXYList = pathXYList;
    }
    public boolean isFill() {
        return isFill;
    }

    public void setIsFill(boolean isFill) {
        this.isFill = isFill;
    }

}
