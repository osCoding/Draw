package com.cyhz.drawgraph.drawgraph.draw.DrawControl;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.cyhz.drawgraph.drawgraph.draw.model.LineModel;


/**
 * Created by qinghua on 2015/8/7.
 */
public class LineControl extends DrawControl {

    private LineModel lineModel;

    public LineControl(Canvas canvas,LineModel lineModel) {
        super(canvas);
        this.lineModel = lineModel;
    }

    public void beginDraw() {
        Paint paint = new Paint();
        paint.setColor(lineModel.getColor());
        paint.setStrokeWidth(lineModel.getStrokeWidth());
        mCanvas.drawLine(lineModel.getBeginX(),lineModel.getBeginY(),lineModel.getEndX(),lineModel.getEndY(),paint);
    }
}
