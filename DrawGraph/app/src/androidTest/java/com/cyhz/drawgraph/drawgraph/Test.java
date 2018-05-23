package com.cyhz.drawgraph.drawgraph;

import android.test.AndroidTestCase;
import android.util.Log;

import com.cyhz.drawgraph.drawgraph.controller.MaxValueInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2015/8/7.
 */
public class Test extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Log.d("sj", "-----setUp----------");
    }

    public void testMaxValue() {

        MaxValueInterpolator maxValueInterpolator = new MaxValueInterpolator();
        float f1 = maxValueInterpolator.getInterpolation(500);
        float f2 = maxValueInterpolator.mathK();
        float f3 = maxValueInterpolator.mathN();

        Log.d("sj", "f1===>" + f1 + "   f2=====>>" + f2 + "     f3---->>" + f3);

    }

    public void testScale() {

//        List<Point> list = getY(2,7,12,27,10);
//        for(int i=0;i<list.size();i++){
//            Point point = list.get(i);
//            Log.d("sj","xxxx--->>"+point.getX()+"    yyyy--->"+point.getY());
//        }
        getScaleValue(9.7f,2.5f);

    }

    public void getScaleValue(float max,float min){
        int maxValue = (int)Math.ceil(max);
        Log.e("sj","maxValue----->>"+maxValue);
        int minValue = (int)Math.floor(min);
        Log.e("sj","minValue---->>"+minValue);
    }


    public List<Point> getY(float x1, float y1, float x2, float y2, int count) {
        List<Point> list = new ArrayList<>();
        float a;
        float b;
        if (x1 == x2) {//横坐标相等
            float tempY = y1;
            for (int i = 0; i < count; i++) {
                Point point = new Point();
                point.setX(x1);
                if (y1 > y2) {
                    if (tempY > y2) {
                        tempY=tempY-1;
                        point.setY(tempY);
                        list.add(point);
                    } else {
                        break;
                    }
                } else {
                    if (tempY < y2) {
                        tempY=tempY+1;
                        point.setY(tempY);
                        list.add(point);
                    }else {
                        break;
                    }
                }

            }
        } else if (y1 == y2) {//纵坐标相等
            float tempX = x1;
            for (int i = 0; i < count; i++) {
                Point point = new Point();
                if (tempX < x2) {
                    tempX = tempX + 1;
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
            list.add(new Point(x1,y1));
            float tempY = 0;
            for (int i = 0; i < count; i++) {
                    Point point = new Point();
                    if (tempX <x2) {
                        tempX = tempX + 1;
                        point.setX(tempX);
                        tempY = a * tempX + b;
                        point.setY(tempY);
                        list.add(point);
                    } else {
                        break;
                    }
                }
            }

            return list;
        }
    }
