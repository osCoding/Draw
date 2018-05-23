package com.cyhz.drawgraph.drawgraph.draw.DrawControl;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

import com.cyhz.drawgraph.drawgraph.draw.model.BitmapModel;

import java.util.List;


/**
 * Created by qinghua on 2015/8/7.
 */
public class BitmapControl extends DrawControl {

    private List<BitmapModel> bitmapModelList;

    public BitmapControl(Canvas canvas, List<BitmapModel> bitmapModelList) {
        super(canvas);
        this.bitmapModelList = bitmapModelList;
    }

    @Override
    public void beginDraw() {
        for(BitmapModel bitmapModel:bitmapModelList){
            RectF rectF = new RectF();
            rectF.left = bitmapModel.getBeginX() - bitmapModel.getWidth()/2;
            rectF.top = bitmapModel.getBeginY() - bitmapModel.getHeight()/2;
            rectF.right = bitmapModel.getBeginX() + bitmapModel.getWidth()/2;
            rectF.bottom = bitmapModel.getBeginY() + bitmapModel.getHeight()/2;
            mCanvas.drawBitmap(bitmapModel.getBitmap(),null,rectF,paint);
//            mCanvas.drawBitmap(bitmapModel.getBitmap(),bitmapModel.getBeginX()-bitmapModel.getBitmap().getWidth()/2,bitmapModel.getBeginY()-bitmapModel.getBitmap().getHeight()/2,paint);
        }
    }
}
