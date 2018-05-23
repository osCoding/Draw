package com.cyhz.drawgraph.drawgraph.controller;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;


import com.cyhz.drawgraph.drawgraph.controller.Constant;
import com.cyhz.drawgraph.drawgraph.controller.MaxValueInterpolator;
import com.cyhz.drawgraph.drawgraph.controller.utils.TextAttributeUtil;
import com.cyhz.drawgraph.drawgraph.draw.model.BitmapModel;
import com.cyhz.drawgraph.drawgraph.draw.model.CirclePointModel;
import com.cyhz.drawgraph.drawgraph.draw.model.DottedLineModel;
import com.cyhz.drawgraph.drawgraph.draw.model.LineModel;
import com.cyhz.drawgraph.drawgraph.draw.model.PathModel;
import com.cyhz.drawgraph.drawgraph.draw.model.PathXY;
import com.cyhz.drawgraph.drawgraph.draw.model.TextModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.DrawModel;


/**
 * Created by MyPC on 2015/8/7.
 */
public class Calculate {

    /**
     * 计算纵坐标起始点X
     * @param maxValueWidth  纵坐标上最大值宽度
     * @return
     */
    public static float getStartVerticalX(View view,float maxValueWidth){
        float x = Constant.LEFT_MARGIN_SCALE*view.getWidth()+Constant.V_TEXT_LINE_MARGIN_SCALE*view.getWidth()+maxValueWidth;
        return x;
    }
    //获取纵坐标起始点Y
    public static float getStartVerticalY(View view){
        float y = Constant.TOP_MARGIN_SCALE*view.getHeight();
        return y;
    }

    //获取纵坐标终点x
    public static float getEndVerticalX(View view,float maxValueWidth){
        return getStartVerticalX(view,maxValueWidth);
    }

    /**
     * 计算纵坐标结束点Y
     * @param view
     * @return
     */
    public static float getEndVerticalY(View view){
        float endY = view.getHeight()-Constant.BOTTOM_MARGIN_SCALE*view.getHeight()-
                Constant.H_TEXT_LINE_MARGIN_SCALE*view.getHeight()- TextAttributeUtil.getTextHeight(Constant.TEXT_SIZE*view.getWidth());
        return endY;
    }

    //获取横坐标终点X

    public static float getEndHorizontalX(View view){
        float horizontalX = view.getWidth()-Constant.RIGHT_MARGIN_SCALE*view.getWidth();
        return horizontalX;
    }

    /**
     * 横坐标结束点的Y值跟纵坐标结束点Y是相等的
     * @return
     */
    public static  float getEndHorizontalY(View view){
        return getEndVerticalY(view);
    }


    /**
     * 计算纵坐标刻度数据及坐标信息
     * @param view
     * @return
     * 刻度的X坐标=纵坐标起始点坐标X-刻度与纵坐标的间距-字符宽度
     * 刻度的Y坐标=纵坐标上边距+字符高度/2+字符见间距
     * 加上字符高度/2，为了保证字符中间对准刻度
     */
    public static List<TextModel> getScaleTextModel(View view,float maxValue){
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);//刻度最大值
        float maxValueWidth = TextAttributeUtil.getTextWidth(String.valueOf(realMaxValue), Constant.TEXT_SIZE*view.getWidth());//最大值宽度
        int num = maxValueInterpolator.mathN();//刻度数量
        int perValue = maxValueInterpolator.mathK();//单位刻度的大小
        List<TextModel> list = new ArrayList<>();
        float verticalHeight = getEndVerticalY(view)-Constant.TOP_MARGIN_SCALE*view.getHeight();//纵坐标总高度
        float unitHeight = verticalHeight/num;//每个刻度所占的单位高度
        float verticalX = getStartVerticalX(view, maxValueWidth)-Constant.V_TEXT_LINE_MARGIN_SCALE*view.getWidth();
        float ascent = TextAttributeUtil.getTextFontMetrics(Constant.TEXT_SIZE*view.getWidth()).ascent;
        float descent= TextAttributeUtil.getTextFontMetrics(Constant.TEXT_SIZE*view.getWidth()).descent;
        float verticalY = (TextAttributeUtil.getTextHeight(Constant.TEXT_SIZE*view.getWidth())+ascent+descent)/2+Constant.TOP_MARGIN_SCALE*view.getHeight();
        for(int i=0;i<num+1;i++){
            String textValue= ((num-i)*perValue)+"";
            float startX = verticalX-TextAttributeUtil.getTextWidth(textValue,Constant.TEXT_SIZE*view.getWidth());
            float startY = verticalY+i*unitHeight;
            TextModel textModel = new TextModel(textValue,Constant.TEXT_SIZE*view.getWidth(),startX,startY,Constant.TEXT_COLOR);
            list.add(textModel);

        }

        return list;

    }


    public static List<TextModel> getScaleTextModel(View view,float minValue,float maxValue){
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);//刻度最大值
        float maxValueWidth = TextAttributeUtil.getTextWidth(String.valueOf(realMaxValue), Constant.TEXT_SIZE*view.getWidth());//最大值宽度
        int num = maxValueInterpolator.mathN();//刻度数量
        float min = maxValueInterpolator.getMinValue(minValue);
        float perValue = maxValueInterpolator.mathK(minValue,maxValue);//单位刻度的大小
        List<TextModel> list = new ArrayList<>();
        float verticalHeight = getEndVerticalY(view)-Constant.TOP_MARGIN_SCALE*view.getHeight();//纵坐标总高度
        float unitHeight = verticalHeight/num;//每个刻度所占的单位高度
        float verticalX = getStartVerticalX(view, maxValueWidth)-Constant.V_TEXT_LINE_MARGIN_SCALE*view.getWidth();
        float ascent = TextAttributeUtil.getTextFontMetrics(Constant.TEXT_SIZE*view.getWidth()).ascent;
        float descent= TextAttributeUtil.getTextFontMetrics(Constant.TEXT_SIZE*view.getWidth()).descent;
        float verticalY = (TextAttributeUtil.getTextHeight(Constant.TEXT_SIZE*view.getWidth())+ascent+descent)/2+Constant.TOP_MARGIN_SCALE*view.getHeight();
        for(int i=0;i<num+1;i++){
            DecimalFormat df = new DecimalFormat("0.000");
            String textValue= df.format(((num-i)*perValue+min));
            Log.e("sj","textValue length:"+textValue.length());
//            String textValue= ((num-i)*perValue+min)+"";
            float startX = verticalX-TextAttributeUtil.getTextWidth(textValue.trim(),Constant.TEXT_SIZE*view.getWidth());
            Log.e("sj","startX:"+startX);
            float startY = verticalY+i*unitHeight;
            TextModel textModel = new TextModel(textValue,Constant.TEXT_SIZE*view.getWidth(),startX,startY,Constant.TEXT_COLOR);
            list.add(textModel);

        }

        return list;

    }





    /**
     * 计算虚线起始点坐标
     * @param view
     * @param maxValue 刻度最大值
     * @return
     */


    public static List<DottedLineModel> getDashedModel(View view,float maxValue){
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);//刻度最大值
        float maxValueWidth = TextAttributeUtil.getTextWidth(realMaxValue + "", Constant.TEXT_SIZE*view.getWidth());
        int num = maxValueInterpolator.mathN();//刻度数量

        List<DottedLineModel> list = new ArrayList<>();
        float verticalHeight = getEndVerticalY(view)-Constant.TOP_MARGIN_SCALE*view.getHeight();//纵坐标总高度
        float unitHeight = verticalHeight/num;//每个刻度所占的单位高度
        float verticalX = getStartVerticalX(view,maxValueWidth);
        float verticalY =Constant.TOP_MARGIN_SCALE*view.getHeight();
        for(int i=0;i<num;i++){
            float startX = verticalX;
            float startY = verticalY+i*unitHeight;
            float endX = getEndHorizontalX(view);
            float endY= startY;

            DottedLineModel dottedLineModel = new DottedLineModel(startX,startY,endX,endY,Constant.DOTTED_LINE_COLOR,2);
            list.add(dottedLineModel);

        }

        return list;

    }


    /**
     * 计算时间坐标轴数据，及坐标
     * @param view
     * @param maxValue
     * @return
     */
    public static List<TextModel> getTimeTextModel(List<DrawModel> list,View view,float maxValue){
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);//刻度最大值
        float maxValueWidth = TextAttributeUtil.getTextWidth(realMaxValue + "", Constant.TEXT_SIZE*view.getWidth());
        float originX = getEndVerticalX(view, maxValueWidth);
        int size = list.size();
        float unitWidth = (getEndHorizontalX(view)-originX-Constant.SPACING_SCALE*view.getWidth())/size;
        float textY = getEndVerticalY(view)+Constant.H_TEXT_LINE_MARGIN_SCALE*view.getHeight()+TextAttributeUtil.getTextHeight(Constant.TEXT_SIZE*view.getWidth());
        List<TextModel> textModels = new ArrayList<>();
        for (int i=size-1;i>=0;i-=7){
            String textValue= list.get(i).getTimeX();
            float textWidth = TextAttributeUtil.getTextWidth(textValue, Constant.TEXT_SIZE * view.getWidth());
            float startX = originX+Constant.SPACING_SCALE*view.getWidth()+i*unitWidth-(textWidth)/2;
            TextModel textModel = new TextModel(textValue,Constant.TEXT_SIZE*view.getWidth(),startX,textY,Constant.H_TEXT_COLOR);
            textModels.add(textModel);
        }
        return textModels;
    }

    /**
     * 计算曲线上显示的数据
     * @param view
     * @param maxValue
     * @return
     */
    public static List<TextModel> getValueTextModel(List<PathXY> valueList,List<DrawModel> drawModelList,View view,double maxValue){
        List<TextModel> textModels = new ArrayList<>();
        for (int i=0;i<valueList.size();i++){
            if(i>=drawModelList.size()){
                break;
            }
            PathXY pathXY = valueList.get(i);
            String textValue = drawModelList.get(i).getPriceY()+"";
            float textWidth = TextAttributeUtil.getTextWidth(textValue, Constant.TEXT_SIZE*view.getWidth());
            float textHeight = TextAttributeUtil.getTextHeight(Constant.TEXT_SIZE*view.getWidth());
            TextModel textModel = new TextModel(textValue,Constant.TEXT_SIZE*view.getWidth(),pathXY.getX()-textWidth/2,pathXY.getY()-textHeight/2,Constant.TEXT_COLOR);
            textModels.add(textModel);
        }
        return textModels;
    }

    /**
     * 横坐标刻度
     * @param list
     * @param view
     * @param maxValue
     * @return
     */
    public static List<LineModel> getHorizontalScale(List<DrawModel> list,View view,float maxValue){

        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);//刻度最大值
        float maxValueWidth = TextAttributeUtil.getTextWidth(realMaxValue + "", Constant.TEXT_SIZE*view.getWidth());
        float originX = getEndVerticalX(view, maxValueWidth);
        int size = list.size();
        float horizontalWidth = getEndHorizontalX(view)-originX-Constant.SPACING_SCALE*view.getWidth();
        float unitWidth = horizontalWidth/size;
        float lineY = getEndVerticalY(view);
        List<LineModel> lineModels = new ArrayList<>();
        for (int i=size-1;i>=0;i-=7){
            float startX = originX+i*unitWidth+Constant.SPACING_SCALE*view.getWidth();
            LineModel lineModel = new LineModel(startX,lineY,startX,lineY-10,Color.BLACK,2);
            lineModels.add(lineModel);
        }

        return lineModels;
    }




    /**
     * 计算点坐标
     * @param bitmap  点图片
     * @param list    表示price集合
     * @param view
     * @param maxValue 纵坐标上的最大值
     * @return
     */

    public static List<BitmapModel> getBitMapModels(Bitmap bitmap,List<Float> list,View view,float maxValue){
        List<BitmapModel> bitmapModels = new ArrayList<>();
        float drawH = getEndVerticalY(view)-Constant.TOP_MARGIN_SCALE*view.getHeight();
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);
        float perH = drawH/realMaxValue;
        int size = list.size();
        for(int i =0;i<size;i++){
            float f = list.get(i);
            float valueH = drawH-perH*f;
            float y = getStartVerticalY(view)+valueH;
            BitmapModel bitmapModel = new BitmapModel(bitmap,5,5,160,y);
            bitmapModels.add(bitmapModel);
        }

        return bitmapModels;
    }


    /**
     * 计算点坐标   将业务model转化坐标点
     * @param bitmap  点 图片
     * @param list    表示price集合
     * @param view
     * @param maxValue 纵坐标上的最大值
     * @return
     */

    public static List<BitmapModel> getBitMapModelList(Bitmap bitmap,List<DrawModel> list,View view,float maxValue){
        List<BitmapModel> bitmapModels = new ArrayList<>();
        float drawH = getEndVerticalY(view)-Constant.TOP_MARGIN_SCALE;//计算绘制Y轴高度
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);
        float maxValueWidth = TextAttributeUtil.getTextWidth(realMaxValue + "", Constant.TEXT_SIZE * view.getWidth());
        float perH = drawH/realMaxValue;//单位纵坐标刻度高度
        int size = list.size();
        float perW = (view.getWidth()- getEndVerticalX(view,maxValueWidth)-
                Constant.RIGHT_MARGIN_SCALE*view.getWidth()-
                Constant.SPACING_SCALE*view.getWidth())/size;//单位横坐标刻度宽度
        for(int i =0;i<size;i++){
            float f = list.get(i).getPriceY();
            float valueH = drawH-perH*f;
            float y = getStartVerticalY(view)+valueH;
            float x = getStartVerticalX(view,maxValueWidth)+i*perW+Constant.SPACING_SCALE*view.getWidth();
            float originX = getEndVerticalX(view, maxValueWidth);
            float nodeIconWidth = (getEndHorizontalX(view)-originX)*Constant.NODE_ICON_SCALE;
            BitmapModel bitmapModel = new BitmapModel(bitmap,nodeIconWidth,nodeIconWidth,x,y);
            bitmapModels.add(bitmapModel);
        }

        return bitmapModels;
    }


    /**
     * 获取绘制点 model集合  <----实际坐标点转化
     * @param bitmap 点图片
     * @param list  坐标轴中的坐标及实际绘制坐标
     * @param view
     * @param maxValue
     * @return
     */

    public static List<BitmapModel> getBitMapModelListByPathXY(Bitmap bitmap,List<PathXY> list,View view,float maxValue){
        List<BitmapModel> bitmapModels = new ArrayList<>();
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);
        float maxValueWidth = TextAttributeUtil.getTextWidth(realMaxValue + "", Constant.TEXT_SIZE*view.getWidth());
        int size = list.size();
        float originX = getEndVerticalX(view, maxValueWidth);
        float nodeIconWidth = (getEndHorizontalX(view)-originX)*Constant.NODE_ICON_SCALE;
        for(int i =0;i<size;i++){
            float y = list.get(i).getY();
            float x = list.get(i).getX();
            BitmapModel bitmapModel = new BitmapModel(bitmap,nodeIconWidth,nodeIconWidth,x,y);
            bitmapModels.add(bitmapModel);
        }

        return bitmapModels;
    }

    public static List<CirclePointModel> getCirclePointListByPathXY(List<PathXY> list,View view,float maxValue){
        List<CirclePointModel> circlePointModelList = new ArrayList<>();
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);
        float maxValueWidth = TextAttributeUtil.getTextWidth(realMaxValue + "", Constant.TEXT_SIZE*view.getWidth());
        int size = list.size();
        float originX = getEndVerticalX(view, maxValueWidth);
        float nodeIconWidth = (getEndHorizontalX(view)-originX)*Constant.NODE_ICON_SCALE;
        for(int i =0;i<size;i++){
            float y = list.get(i).getY();
            float x = list.get(i).getX();
            CirclePointModel circlePointModel = new CirclePointModel(x,y,nodeIconWidth,(float)(nodeIconWidth*0.6),Constant.BIG_CIRCLE_POINT_COLOR,Constant.SMALL_CIRCLE_POINT_COLOR);
            circlePointModelList.add(circlePointModel);
        }
        return circlePointModelList;
    }


    //计算点坐标

    public static List<PathXY> getBitMapModelList(List<DrawModel> list,View view,float minValue,float maxValue){
        List<PathXY> bitmapModels = new ArrayList<>();
        float drawH = getEndVerticalY(view)-Constant.TOP_MARGIN_SCALE*view.getHeight();//计算绘制Y轴高度
        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float realMaxValue = maxValueInterpolator.getInterpolation(maxValue);
        float min = maxValueInterpolator.getMinValue(minValue);
        float maxValueWidth = TextAttributeUtil.getTextWidth(realMaxValue+"",Constant.TEXT_SIZE*view.getWidth());
        float perH = drawH/(realMaxValue-min);
        int size = list.size();
        float perW = (view.getWidth()- getEndVerticalX(view,maxValueWidth)-Constant.RIGHT_MARGIN_SCALE*view.getWidth()-Constant.SPACING_SCALE*view.getWidth())/size;
        for(int i =0;i<size;i++){
            float f = list.get(i).getPriceY();
            float valueH = drawH-perH*(f-min);
            float y = getStartVerticalY(view)+valueH;
            float x = getStartVerticalX(view,maxValueWidth)+i*perW+Constant.SPACING_SCALE*view.getWidth();
            PathXY pathXY = new PathXY(x,y);
            bitmapModels.add(pathXY);
        }

        return bitmapModels;
    }

    /**
     * 获取显示数据的点坐标list
     * @param pathXYList
     * @return
     */
    public static List<PathXY> getShowValuePathList(List<PathXY> pathXYList){
        List<PathXY> showValuePathList = new ArrayList<>();
        for(int i=pathXYList.size()-1;i>=0;i-=7){
            showValuePathList.add(pathXYList.get(i));
        }
        List<PathXY> pathXYList1 = new ArrayList<>();
        for(int j=showValuePathList.size()-1;j>=0;j--){
            pathXYList1.add(showValuePathList.get(j));
        }
        return pathXYList1;
    }

    /**
     * 获取显示数据list
     * @param drawModelList
     * @return
     */
    public static List<DrawModel> getShowValueModelList(List<DrawModel> drawModelList){
        List<DrawModel> drawModels = new ArrayList<>();
        for(int i=drawModelList.size()-1;i>=0;i-=7){
            drawModels.add(drawModelList.get(i));
        }
        List<DrawModel> drawModelList1 = new ArrayList<>();
        for(int j=drawModels.size()-1;j>=0;j--){
            drawModelList1.add(drawModels.get(j));
        }
        return drawModelList1;
    }

    /**
     * 获取绘制path数据点坐标
     * @param bitmap
     * @param list
     * @param view
     * @param maxValue
     * @param isFill
     * @return
     */

    public static PathModel getPathModels(Bitmap bitmap,List<DrawModel> list,View view,float maxValue,boolean isFill){
        List<BitmapModel> bitmapModels = getBitMapModelList(bitmap,list,view,maxValue);
        List<PathXY> pathList= new ArrayList<>();
        for(BitmapModel bitmapModel: bitmapModels){
            float x = bitmapModel.getBeginX();
            float y = bitmapModel.getBeginY();
            PathXY pathXY = new PathXY();
            pathXY.setX(x);
            pathXY.setY(y);
            pathList.add(pathXY);
        }
        PathModel pathModel;
        if(isFill){
            if(pathList.size()>1){
                PathXY pathXY = new PathXY();
                PathXY pathXY1 = pathList.get(0);
                pathXY.setX(pathXY1.getX());
                pathXY.setY(getEndVerticalY(view)-3);
                pathList.add(0, pathXY);

                PathXY lastPath = new PathXY();
                PathXY pathXY2 = pathList.get(pathList.size()-1);
                lastPath.setX(pathXY2.getX());
                lastPath.setY(getEndVerticalY(view)-3);
                pathList.add(lastPath);
            }
            pathModel = new PathModel(pathList,isFill,Constant.FILL_COLOR,2);
        }else {
            pathModel = new PathModel(pathList,isFill,Constant.PATH_COLOR,2);
        }
        return pathModel;
    }

    //根据pathxy 获取pathModel
    public static PathModel getPathModels(List<PathXY> pathList,View view,boolean isFill){
        PathModel pathModel;
        List<PathXY> pathXYList = new ArrayList<>(pathList);
        if(isFill) {
            if (pathList.size() > 0) {
                PathXY pathXY = new PathXY();
                PathXY pathXY1 = pathList.get(0);
                pathXY.setX(pathXY1.getX());
                pathXY.setY(getEndVerticalY(view)-2);
                pathList.add(0, pathXY);

                PathXY lastPath = new PathXY();
                PathXY pathXY2 = pathList.get(pathList.size() - 1);
                lastPath.setX(pathXY2.getX());
                lastPath.setY(getEndVerticalY(view)-2);
                pathList.add(lastPath);
            }
            pathModel = new PathModel(pathList, isFill, Constant.FILL_COLOR, 2);
        }else {
            pathModel = new PathModel(pathXYList, isFill, Constant.PATH_COLOR, 2);
        }
        return pathModel;
    }

    //根据业务点，通过直线计算pathxy
    public static List<PathXY> getPathList(List<DrawModel> list,View view,float minValue,float maxValue){

        List<PathXY> originPathList = getBitMapModelList(list,view,minValue,maxValue);
        List<PathXY> handPointList = new ArrayList<>();
        int size = originPathList.size();
        if(size>0){
            for(int i=0;i<size;i++){
                if(i>0){
                    PathXY pathXY1 = originPathList.get(i-1);
                    PathXY pathXY2 = originPathList.get(i);
                    float x1 = pathXY1.getX();
                    float y1 = pathXY1.getY();
                    float x2 = pathXY2.getX();
                    float y2 = pathXY2.getY();
                    List<PathXY> paths = getPathAllPoints(x1,y1,x2,y2);
                    handPointList.addAll(paths);
                }
            }

            handPointList.add(0,originPathList.get(0));
        }
        return handPointList;
    }


//获取两点之间所有点

    public static List<PathXY> getPathAllPoints(float x1, float y1, float x2, float y2) {
        List<PathXY> list = new ArrayList<>();
        float a;
        float b;
        if (x1 == x2) {//横坐标相等
            float tempY = y1;
            while(true){
                PathXY point = new PathXY();
                point.setX(x1);
                if (y1 > y2) {
                    if (tempY > y2) {
                        tempY=tempY-1;
                        if(tempY<=y2){
                            tempY=y2;
                        }
                        point.setY(tempY);
                        list.add(point);
                    } else {
                        break;
                    }
                } else {
                    if (tempY < y2) {
                        tempY=tempY+1;
                        if(tempY>=y2){
                            tempY=y2;
                        }
                        point.setY(tempY);
                        list.add(point);
                    }else {
                        break;
                    }
                }

            }
        } else if (y1 == y2) {//纵坐标相等
            float tempX = x1;
            while (true){
                PathXY point = new PathXY();
                if (tempX < x2) {
                    tempX = tempX + 1;
                    if(tempX>=x2){
                        tempX=x2;
                    }
                    point.setX(tempX);
                    point.setY(y1);
                    list.add(point);

                } else {
                    break;
                }
            }
        } else {
            a = (y2 - y1) / (x2 - x1);
            b = y2 - a * x2;
            float tempX = x1;
            float tempY = 0;
            while (true){
                PathXY point = new PathXY();
                if (tempX <x2) {
                    tempX = tempX + 0.5f;
                    if(tempX>=x2){
                        tempX=x2;
                        tempY=y2;
                    }
                    point.setX(tempX);
                    tempY = a * tempX + b;
                    if(tempX==x2){
                        tempY=y2;
                    }
                    point.setY(tempY);
                    list.add(point);
                } else {
                    break;
                }
            }
        }

        return list;
    }


    /**
     * 获取两个集合内相同的点
     * @param list1
     * @param list2
     * @return
     */
    public static List<PathXY> getDotsList(List<PathXY> list1,List<PathXY> list2){
        HashMap<String,PathXY> hashMap = new HashMap<>();
        List<PathXY> list = new ArrayList<>();
        for(PathXY pathXY:list2){
            float x = pathXY.getX();
            float y = pathXY.getY();
            hashMap.put(x+"_"+y,pathXY);
        }

        for(PathXY pathXY:list1){
            String key = pathXY.getX()+"_"+pathXY.getY();
            if(hashMap.containsKey(key)){
                list.add(pathXY);
            }
        }
        return list;
    }

}
