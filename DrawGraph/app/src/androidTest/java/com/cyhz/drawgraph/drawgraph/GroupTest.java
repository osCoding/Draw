package com.cyhz.drawgraph.drawgraph;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import transmission.AccelerateTransmission;

/**
 * Created by liuxiaolong on 15/8/12.
 */
public class GroupTest extends AndroidTestCase {
    public void testGroup(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            list.add(i);
        }
        AccelerateTransmission in = new AccelerateTransmission();
        String tag = in.dataGrouping(5, list).toString();
        Log.e("lxl...log...testGroup",tag);
    }
}
