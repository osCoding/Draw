package com.cyhz.drawgraph.drawgraph.draw.DrawControl;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;

import com.cyhz.drawgraph.drawgraph.draw.model.DottedLineModel;

/**
 * 绘制虚线
 * Created by qinghua on 2015/8/7.
 */
public class DottedLineControl extends DrawControl{
    private DottedLineModel dottedLineModel;

    public DottedLineControl(Canvas canvas, DottedLineModel dottedLineModel) {
        super(canvas);
        this.dottedLineModel = dottedLineModel;
    }

    @Override
    public void beginDraw() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dottedLineModel.getStrokeWidth());
        paint.setColor(dottedLineModel.getColor());
        Path path = new Path();
        path.moveTo(dottedLineModel.getBeginX(), dottedLineModel.getBeginY());
        path.lineTo(dottedLineModel.getEndX(), dottedLineModel.getEndY());
        PathEffect effects = new DashPathEffect(new float[]{5,5},0);
        paint.setPathEffect(effects);
        mCanvas.drawPath(path, paint);
    }
}
