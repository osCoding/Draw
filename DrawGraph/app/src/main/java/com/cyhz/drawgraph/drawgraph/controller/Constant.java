package com.cyhz.drawgraph.drawgraph.controller;

import android.graphics.Color;

import java.math.BigDecimal;

/**
 * Created by MyPC on 2015/8/8.
 */
public class Constant {
    public static final float TEXT_SIZE=16f/720;//绘制文本的字体大小，px单位
    public static final float NODE_ICON_SCALE= 4.0f/638.0f;//节点图片大小比例
    public static final int TEXT_COLOR= Color.parseColor("#000000");
    public static final int H_TEXT_COLOR=Color.parseColor("#333333");
    public static final int LINE_COLOR=Color.parseColor("#1081e0");
    public static final int FILL_COLOR=Color.parseColor("#cfe6f9");
    public static final int PATH_COLOR=Color.parseColor("#cfe6f9");
    public static final int DOTTED_LINE_COLOR=Color.parseColor("#e5e5e5");
    public static final int BIG_CIRCLE_POINT_COLOR=Color.parseColor("#9FCDF3");
    public static final int SMALL_CIRCLE_POINT_COLOR=Color.parseColor("#1081E0");



    public static final float LEFT_MARGIN_SCALE=20f/720;//绘图区域距离View左边距离
    public static final float V_TEXT_LINE_MARGIN_SCALE=10f/720;//文字与纵坐标间的间距
    public static final float TOP_MARGIN_SCALE=15f/480;//绘制区域跟view顶部间距
    public static final float RIGHT_MARGIN_SCALE=20f/720;//绘制去跟view右部的间距
    public static final float BOTTOM_MARGIN_SCALE=40f/480;//底部时间与view底部的间距
    public static final float SPACING_SCALE=60f/720;//坐标中的绘图区间隙
    public static final float H_TEXT_LINE_MARGIN_SCALE=14f/480;//文字与横坐标间的间距


}
