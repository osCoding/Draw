package com.cyhz.drawgraph.drawgraph.draw;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;

import com.cyhz.drawgraph.drawgraph.R;
import com.cyhz.drawgraph.drawgraph.controller.Calculate;
import com.cyhz.drawgraph.drawgraph.controller.DrawGraphController;
import com.cyhz.drawgraph.drawgraph.draw.model.PathModel;
import com.cyhz.drawgraph.drawgraph.draw.model.PathXY;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.DrawModel;
import transmission.AbsTransmission;
import transmission.AbsTransmissionSupport;
import transmission.AccelerateTransmission;

/**
 * Created by qinghua on 2015/8/7.
 */
public class DrawActivity extends Activity implements TextureView.SurfaceTextureListener{

    private DrawView draw_view_dv;
    private DrawGraphController controller;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Log.e("sj","==========Activity   handler======");
                init();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_layout);
        draw_view_dv = (DrawView) findViewById(R.id.draw_view_dv);
//        draw_view_dv.setSurfaceTextureListener(this);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lansequan);
        controller = new DrawGraphController(draw_view_dv,bitmap);
        Message message = new Message();
        message.what=1;
        handler.sendMessageDelayed(message,2000);
    }


    private  void init(){
        List<String> list = new ArrayList<>();
        list.add("07-05");
        list.add("07-10");
        list.add("07-15");
//        list.add("07-20");
//        list.add("07-25");
//        list.add("07-30");
//        list.add("08-04");
//        list.add("08-10");
//        list.add("07-05");
//        list.add("07-10");
//        list.add("07-15");
//        list.add("07-20");
//        list.add("07-25");
//        list.add("07-30");
//        list.add("08-04");
//        list.add("08-10");
//        list.add("07-05");
//        list.add("07-10");
//        list.add("07-15");
//        list.add("07-20");
//        list.add("07-25");
//        list.add("07-30");
//        list.add("08-04");
//        list.add("08-10");
//        list.add("07-05");
//        list.add("07-10");
//        list.add("07-15");
//        list.add("07-20");
//        list.add("07-25");
//        list.add("07-30");
//        list.add("08-04");
        List<Float> datas = new ArrayList<>();
        datas.add(15.18f);
        datas.add(15.18f);
        datas.add(15.18f);
//        datas.add(15.19f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.19f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.19f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.19f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.19f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.19f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.19f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        datas.add(15.18f);
//        controller.drawDot(bitmap, datas);

        final List<DrawModel> drawModels = new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            DrawModel drawModel = new DrawModel();
            drawModel.setPriceY(datas.get(i));
            drawModel.setTimeX(list.get(i));
            drawModels.add(drawModel);
        }

        controller.setData(drawModels);
    }

    private void startDraw(){

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lansequan);
        final DrawGraphController controller = new DrawGraphController(draw_view_dv,bitmap);
        List<String> list = new ArrayList<>();
        list.add("07-05");
        list.add("07-10");
        list.add("07-15");
        list.add("07-20");
        list.add("07-25");
        list.add("07-30");
        list.add("08-04");
        list.add("08-10");
        List<Float> datas = new ArrayList<>();
        datas.add(90f);
        datas.add(13f);
        datas.add(130f);
        datas.add(50f);
        datas.add(70f);
        datas.add(56f);
        datas.add(99f);
        datas.add(103f);
//        controller.drawDot(bitmap, datas);

       final List<DrawModel> drawModels = new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            DrawModel drawModel = new DrawModel();
            drawModel.setPriceY(datas.get(i));
            drawModel.setTimeX(list.get(i));
            drawModels.add(drawModel);
        }

        controller.setData(drawModels);

    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        startDraw();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
}
