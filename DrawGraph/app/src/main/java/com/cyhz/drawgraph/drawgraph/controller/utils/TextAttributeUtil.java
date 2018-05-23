package com.cyhz.drawgraph.drawgraph.controller.utils;

import android.graphics.Paint;

/**
 * Created by MyPC on 2015/8/8.
 */
public class TextAttributeUtil {

    public static float getTextWidth(String str,float textSize){
        Paint paint = new Paint();
        paint.setTextSize(textSize);

        float width=paint.measureText(str);
//        if (str != null && str.length() > 0) {
//            int len = str.length();
//            float[] widths = new float[len];
//            paint.getTextWidths(str, widths);
//            for (int j = 0; j < len; j++) {
//                width += (int) Math.ceil(widths[j]);
//            }
//        }

        return width;
    }

    public static float getTextHeight(float textSize){
          float height =0;
         height = Math.abs(getTextFontMetrics(textSize).ascent)+Math.abs(getTextFontMetrics(textSize).descent);
        return height;
    }

    public static Paint.FontMetrics getTextFontMetrics(float size) {
        Paint paint = new Paint();
        paint.setTextSize(size);
        return paint.getFontMetrics();
    }
}
