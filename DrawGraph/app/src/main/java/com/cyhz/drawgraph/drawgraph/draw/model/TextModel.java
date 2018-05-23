package com.cyhz.drawgraph.drawgraph.draw.model;

/**
 * Created by qinghua on 2015/8/7.
 */
public class TextModel extends DrawBaseModel {
    private float textSize;
    private String textContent;
    private float beginX;
    private float beginY;

    public TextModel(String textContent,float textSize, float beginX, float beginY,int color) {
        this.textSize = textSize;
        this.textContent = textContent;
        this.beginX = beginX;
        this.beginY = beginY;
        this.setColor(color);
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
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
