package com.cyhz.drawgraph.drawgraph.draw.DrawControl;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by qinghua on 2015/8/7.
 */
public abstract class DrawControl {
    public Paint paint;
    public Canvas mCanvas;

    public DrawControl(Canvas canvas) {
        this.paint =new Paint();
        paint.setAntiAlias(true);
        this.mCanvas = canvas;
    }

    public abstract void beginDraw();
}
