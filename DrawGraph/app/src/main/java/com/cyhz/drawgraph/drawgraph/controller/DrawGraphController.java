package com.cyhz.drawgraph.drawgraph.controller;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;

import com.cyhz.drawgraph.drawgraph.controller.utils.TextAttributeUtil;
import com.cyhz.drawgraph.drawgraph.draw.DrawView;
import com.cyhz.drawgraph.drawgraph.draw.model.BitmapModel;
import com.cyhz.drawgraph.drawgraph.draw.model.CirclePointModel;
import com.cyhz.drawgraph.drawgraph.draw.model.DottedLineModel;
import com.cyhz.drawgraph.drawgraph.draw.model.LineModel;
import com.cyhz.drawgraph.drawgraph.draw.model.PathModel;
import com.cyhz.drawgraph.drawgraph.draw.model.PathXY;
import com.cyhz.drawgraph.drawgraph.draw.model.TextModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.DrawModel;
import transmission.AbsTransmission;
import transmission.AbsTransmissionSupport;
import transmission.AccelerateTransmission;

/**
 * Created by MyPC on 2015/8/8.
 */
public class DrawGraphController implements TextureView.SurfaceTextureListener{

    private DrawView drawView;
    private float maxValue,minValue;
    private List<DrawModel> drawModels;
    private Bitmap bitmap;
    private int delayTime=1000;
    private boolean isAvailable;
    private boolean isHasData;
    private boolean isStopDraw;// 是否停止绘画（Activity的onDestroy）
    private ScheduledExecutorService scheduledExecutorService;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Log.e("sj","controller handler");
                start();
            }
        }
    };

    public DrawGraphController(DrawView drawView,Bitmap bitmap) {
        this.drawView = drawView;
        this.bitmap = bitmap;
        drawView.setSurfaceTextureListener(this);
    }

    public void setData(List<DrawModel> drawModels){
        this.drawModels = drawModels;
        if(drawModels!=null&&drawModels.size()>0){
            minValue = drawModels.get(0).getPriceY();
        }
        for(DrawModel drawModel:drawModels){
            float price = drawModel.getPriceY();
            if(maxValue<price){
                maxValue=price;
            }

            if(minValue>price){
                minValue=price;

            }
        }

        if(maxValue==minValue){
            int minScale = (int)minValue;//最小刻度值
            float temp = minValue-minScale;//中位数与最小值差
            Log.e("sj","temp:"+temp);
            maxValue = minValue+temp;//最大值
            minValue = minScale;
            Log.e("sj","maxValue:"+maxValue);
        }else {
            float temp = maxValue-minValue;
            maxValue = maxValue+temp;
            minValue = minValue-temp;
        }
        DecimalFormat df = new DecimalFormat("0.000");
        String maxString= df.format(maxValue);
        maxValue = Float.valueOf(maxString);
        String minString = df.format(minValue);
        minValue=Float.valueOf(minString);
        isHasData = true;
    }

    public void setDelayTime(int delayTime){
        this.delayTime = delayTime;
    }

    public void setIsStopDraw(boolean isStopDraw) {
        this.isStopDraw = isStopDraw;
    }

    /**
     * 画坐标轴
     */
    private void drawCoordinate(){
        //纵轴
        float startX,startY,endX,endY;
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float maxValueWidth = TextAttributeUtil.getTextWidth(maxValueInterpolator.getInterpolation(maxValue) + "",
                Constant.TEXT_SIZE*drawView.getWidth());
        startX=Calculate.getStartVerticalX(drawView,maxValueWidth);
        startY=Calculate.getStartVerticalY(drawView);
        endX = startX;
        endY = Calculate.getEndVerticalY(drawView);
        LineModel lineModel = new LineModel(startX,startY,endX,endY, Constant.LINE_COLOR,3);
        drawView.drawLine(lineModel);
//     横轴
        float hX,hY;
        hX =Calculate.getEndHorizontalX(drawView);
        hY=endY;
        LineModel hLin = new LineModel(endX,endY,hX,hY,Constant.LINE_COLOR,3);
        drawView.drawLine(hLin);

    }

    //画刻度对应的值
    private void drawScaleValue(){
        List<TextModel> list = Calculate.getScaleTextModel(drawView, minValue, maxValue);
        for(TextModel textModel:list){
            drawView.drawText(textModel);
        }
    }

    //画虚线
    private void drawDashed(){
        List<DottedLineModel> list = Calculate.getDashedModel(drawView, maxValue);
        for(DottedLineModel dottedLineModel:list){
            drawView.drawDottedLine(dottedLineModel);
        }
    }

    //画横坐标对应文本
    private void drawTimeText(List<DrawModel> list){
        List<TextModel> textModels = Calculate.getTimeTextModel(list, drawView, maxValue);
        for(TextModel textModel:textModels){
            drawView.drawText(textModel);
        }
    }

    //画对应显示的数值
    private void drawValueText(List<PathXY> valueList,List<DrawModel> drawModelList){
        List<TextModel> textModels = Calculate.getValueTextModel(valueList, drawModelList, drawView, maxValue);
        for(TextModel textModel:textModels){
            drawView.drawText(textModel);
        }
    }

    //画点

    private void drawDots(Bitmap bitmap,List<DrawModel> list){
        List<BitmapModel> bitmapModels = Calculate.getBitMapModelList(bitmap, list, drawView, maxValue);
        drawView.drawBitmaps(bitmapModels);
    }

    private void drawDotsByPathXY(Bitmap bitmap,List<PathXY> list){
        List<BitmapModel> bitmapModels = Calculate.getBitMapModelListByPathXY(bitmap, list, drawView, maxValue);
        drawView.drawBitmaps(bitmapModels);
    }

    private void drawDotsByPathXY(List<PathXY> list){
        List<CirclePointModel> circlePointModelList = Calculate.getCirclePointListByPathXY(list, drawView, maxValue);
        drawView.drawCirclePoints(circlePointModelList);
    }

    //画Path

    private void drawPath(Bitmap bitmap,List<DrawModel> list,boolean isFill){
        PathModel pathModel = Calculate.getPathModels(bitmap, list, drawView, maxValue, isFill);
        drawView.drawPath(pathModel);
    }

    private void DrawPath(PathModel pathModel){
        drawView.drawPath(pathModel);
    }

    //画横坐标刻度
    private void drawHorizontalScale(List<DrawModel> list){
        List<LineModel> lineModels = Calculate.getHorizontalScale(list, drawView, maxValue);
        for(LineModel lineModel:lineModels){
            drawView.drawLine(lineModel);
        }
    }

    private void start(){

        final List<PathXY> pathXYList = Calculate.getPathList(drawModels, drawView, minValue,maxValue);
        final List<PathXY> originPathList = Calculate.getBitMapModelList(drawModels, drawView,minValue, maxValue);
        final List<PathXY> showValuePathList = Calculate.getShowValuePathList(originPathList);
        final List<DrawModel> showValueModelList = Calculate.getShowValueModelList(drawModels);
        AbsTransmission absTransmission = new AccelerateTransmission();
        absTransmission.transmission(new AbsTransmissionSupport<PathXY>() {
            @Override
            public List<PathXY> getTotalAtomic() {
//                    Log.e("sj","pathXYList--->"+pathXYList.toString());
//                    Log.e("sj","originPathList--->"+originPathList.toString());
                return pathXYList;
            }

            @Override
            public int getDuration() {
                return delayTime;
            }

            @Override
            public void go(List<PathXY> atomics) {
                if(!isStopDraw){
                    drawView.getCanvas();
                    List<PathXY> list1 = new ArrayList<PathXY>(atomics);
                    List<PathXY> dotsList = Calculate.getDotsList(list1, originPathList);
                    List<PathXY> valueList = Calculate.getDotsList(list1, showValuePathList);
                    PathModel pathModel1 = Calculate.getPathModels(list1, drawView, false);
                    PathModel pathModel = Calculate.getPathModels(list1, drawView, true);
                    drawCoordinate();
                    drawScaleValue();
//                        drawDashed();
                    drawTimeText(drawModels);
                    DrawPath(pathModel);//path填充
//                        DrawPath(pathModel1);//path不填充
//                        drawDotsByPathXY(bitmap, dotsList);
                    drawDotsByPathXY(dotsList);
                    drawValueText(valueList,showValueModelList);
                    drawHorizontalScale(drawModels);
                    drawView.drawFinish();
                }
            }
        });
    }

    private void startTimer(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(isHasData&&isAvailable){
                    handler.sendEmptyMessage(0);
                    scheduledExecutorService.shutdown();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }



    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        isAvailable = true;
        Log.e("sj","===onSurfaceTextureAvailable====");
        if(isHasData){
            start();
        }else {
            startTimer();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
}
