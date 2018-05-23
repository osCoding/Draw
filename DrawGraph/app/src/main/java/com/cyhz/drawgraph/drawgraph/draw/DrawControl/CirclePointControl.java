package com.cyhz.drawgraph.drawgraph.draw.DrawControl;

import android.graphics.Canvas;


import com.cyhz.drawgraph.drawgraph.draw.model.CirclePointModel;

import java.util.List;

/**
 * Created by qinghua on 2015/8/25.
 */
public class CirclePointControl extends DrawControl {
    private List<CirclePointModel> circlePointModelList;
    public CirclePointControl(Canvas canvas, List<CirclePointModel> circlePointModelList) {
        super(canvas);
        this.circlePointModelList = circlePointModelList;
    }

    @Override
    public void beginDraw() {
        for(CirclePointModel circlePointModel: circlePointModelList){
            paint.setColor(circlePointModel.getBigCricleColor());
            mCanvas.drawCircle(circlePointModel.getCx(), circlePointModel.getCy(), circlePointModel.getBigCricleRadius(), paint);
            paint.setColor(circlePointModel.getSmallCricleColor());
            mCanvas.drawCircle(circlePointModel.getCx(),circlePointModel.getCy(),circlePointModel.getSmallCricleRadius(),paint);
        }
    }
}
