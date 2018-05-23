package com.cyhz.drawgraph.drawgraph.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.TextureView;

import com.cyhz.drawgraph.drawgraph.draw.DrawControl.BitmapControl;
import com.cyhz.drawgraph.drawgraph.draw.DrawControl.CirclePointControl;
import com.cyhz.drawgraph.drawgraph.draw.DrawControl.DottedLineControl;
import com.cyhz.drawgraph.drawgraph.draw.DrawControl.LineControl;
import com.cyhz.drawgraph.drawgraph.draw.DrawControl.PathControl;
import com.cyhz.drawgraph.drawgraph.draw.DrawControl.TextControl;
import com.cyhz.drawgraph.drawgraph.draw.model.BitmapModel;
import com.cyhz.drawgraph.drawgraph.draw.model.CirclePointModel;
import com.cyhz.drawgraph.drawgraph.draw.model.DottedLineModel;
import com.cyhz.drawgraph.drawgraph.draw.model.LineModel;
import com.cyhz.drawgraph.drawgraph.draw.model.PathModel;
import com.cyhz.drawgraph.drawgraph.draw.model.TextModel;

import java.util.List;


/**
 * Created by qinghua on 2015/8/7.
 */
public class DrawView extends TextureView{

    public Canvas mCanvas;
    // 默认白色背景
    private String backgroundColor = "#ffffff";

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置背景颜色
     * @param color
     */
    public void setBackgroundColor(String color){
        this.backgroundColor = color;
    }

    /**
     * 获取Canvas
     */
    public void getCanvas(){
        mCanvas = lockCanvas();
        mCanvas.drawColor(Color.parseColor(backgroundColor));
    }

    /**
     * 画完
     */
    public void drawFinish(){
        unlockCanvasAndPost(mCanvas);
    }

    public void drawLine(LineModel lineModel){
        LineControl lineControl = new LineControl(mCanvas,lineModel);
        lineControl.beginDraw();
    }

    public void drawText(TextModel textModel){
        TextControl textControl = new TextControl(mCanvas,textModel);
        textControl.beginDraw();
    }

    public void drawBitmaps(List<BitmapModel> bitmapModelList){
        BitmapControl bitmapControl = new BitmapControl(mCanvas,bitmapModelList);
        bitmapControl.beginDraw();
    }

    public void drawPath(PathModel pathModel){
        PathControl pathControl = new PathControl(mCanvas,pathModel);
        pathControl.beginDraw();
    }

    public void drawDottedLine(DottedLineModel dottedLineModel){
        DottedLineControl dottedLineControl = new DottedLineControl(mCanvas,dottedLineModel);
        dottedLineControl.beginDraw();
    }

    public void drawCirclePoints(List<CirclePointModel> circlePointModelList){
        CirclePointControl circlePointControl = new CirclePointControl(mCanvas,circlePointModelList);
        circlePointControl.beginDraw();
    }

}
