package com.cyhz.drawgraph.drawgraph.draw.DrawControl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.cyhz.drawgraph.drawgraph.draw.model.PathModel;

/**
 * Created by qinghua on 2015/8/7.
 */
public class PathControl extends DrawControl{

    private PathModel pathModel;

    public PathControl(Canvas canvas,PathModel pathModel) {
        super(canvas);
        this.pathModel = pathModel;
    }

    @Override
    public void beginDraw() {
        Path path = new Path();
        paint.setColor(pathModel.getColor());
        paint.setStrokeWidth(pathModel.getStrokeWidth());

        if(pathModel.isFill()){
            paint.setStyle(Paint.Style.FILL);
        }else{
            paint.setStyle(Paint.Style.STROKE);
        }
        for(int i=0;i<pathModel.getPathXYList().size();i++){
            if(i==0){
                path.moveTo(pathModel.getPathXYList().get(i).getX(),pathModel.getPathXYList().get(i).getY());
            }else{
                path.lineTo(pathModel.getPathXYList().get(i).getX(), pathModel.getPathXYList().get(i).getY());
            }
        }
        mCanvas.drawPath(path,paint);
    }
}
