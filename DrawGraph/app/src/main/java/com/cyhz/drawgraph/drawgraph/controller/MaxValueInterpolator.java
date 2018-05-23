package com.cyhz.drawgraph.drawgraph.controller;

import android.util.Log;
import android.view.animation.Interpolator;

/**
 * Created by liuxiaolong on 15-4-9.
 */
public class MaxValueInterpolator implements Interpolator {

    private float mValueMax;
    private int unit;


    @Override
    public float getInterpolation(float max){

        return max;
    }

    public float getMinValue(float min){
        return min;
    }

    public float mathK(float min,float max){
//        int minValue = (int)Math.floor(min);
//        int maxValue = getInterpolation(max);
        return (max-min)/4;
    }




    /**
     * 获取刻度大小。
     *
     * @return
     */
    public int mathK() {
        if (mValueMax <= 10) {
            return 5;
        } else {
            return ((int) (mValueMax / 5)) + 1;
        }
    }

    /**
     * 获取刻度数量。
     *
     * @return
     */
    public int mathN() {
        return 4;
    }

}
