package com.cyhz.drawgraph.drawgraph.draw.DrawControl;

import android.graphics.Canvas;

import com.cyhz.drawgraph.drawgraph.draw.model.TextModel;

/**
 * Created by qinghua on 2015/8/7.
 */
public class TextControl extends DrawControl {

    private TextModel textModel;

    public TextControl(Canvas canvas,TextModel textModel) {
        super(canvas);
        this.textModel = textModel;
    }

    @Override
    public void beginDraw() {
        paint.setColor(textModel.getColor());
        paint.setTextSize(textModel.getTextSize());
        mCanvas.drawText(textModel.getTextContent(),textModel.getBeginX(),textModel.getBeginY(),paint);
    }
}
